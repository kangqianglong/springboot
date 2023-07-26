package lianghua;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.text.DecimalFormat;

public class MeanReversionStrategy2 {

    private static final String STOCK_CODE = "sz000002"; // ��Ʊ����
    private static final int LOOKBACK_PERIOD = 20; // �ؿ�����
    private static final double MEAN = 0.0; // ��ֵ
    private static final double STDEV = 1.0; // ��׼��
    private static double cash = 100000.0; // ��ʼ�ʽ�

    public static void main(String[] args) {
        List<Double> prices = fetchStockData();
        List<Double> returns = calculateReturns(prices);
        double mean = calculateMean(returns);
        double stdev = calculateStdev(returns, mean);
        double lowerThreshold = MEAN - STDEV;
        double upperThreshold = MEAN + STDEV;

        System.out.println("��ֵ " + mean);
        System.out.println("��׼�� " + stdev);

        double position = 0.0;

        for (int i = LOOKBACK_PERIOD; i < prices.size(); i++) {
            double currentPrice = prices.get(i);
            double prevPrice = prices.get(i - LOOKBACK_PERIOD);
            double previousReturn = (prevPrice - currentPrice) / prevPrice;
            double currentReturn = (prices.get(i - 1) - currentPrice) / prices.get(i - 1);
            double diff = mean - previousReturn;
            boolean inRange = (previousReturn >= lowerThreshold && previousReturn <= upperThreshold);

            if (inRange && diff > 0) {
                // �۸��ڷ�Χ֮�����½�������
                double buyPrice = currentPrice;
                int units = (int) (cash / buyPrice);
                position += units;
                cash -= units * buyPrice;
                System.out.println("[" + i + "] �����Ʊ " + units + " �� @ " + buyPrice + " �ܽ�� " + (units * buyPrice) + " ʣ���ֽ� " + cash);

            } else if (!inRange || diff < 0) {
                // �۸��ڷ�Χ֮�������������
                double sellPrice = currentPrice;
                cash += position * sellPrice;
                System.out.println("[" + i + "] ������Ʊ " + position + " �� @ " + sellPrice + " �ܽ�� " + (position * sellPrice) + " ʣ���ֽ� " + cash);
                position = 0.0;
            }
        }
        
        // ����ز���
        double pnl = cash + position * prices.get(prices.size() - 1);
        double roi = (pnl - 100000) / 100000;
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.println("�ز�������������гֹ���: " + position + " ���й�Ʊ��ֵ: " + position * prices.get(prices.size() - 1) + " ��ǰ���ʲ�: " + pnl + " ������: " + df.format(roi*100) + "%");
    }

    private static List<Double> fetchStockData() {
        List<Double> prices = new ArrayList<Double>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"), Locale.getDefault());
        Date toDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date fromDate = calendar.getTime();
        String urlString = String.format(
            "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183016655650264337415_1620577503089&p=1&ps=400&isPagination=true&sortType=1&prd=Q&source=WEB&date=%s&startTime=%s&endTime=%s&sr=1620577583876&_=1620577583876",
            STOCK_CODE, dateFormat.format(fromDate), dateFormat.format(toDate));
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.addRequestProperty("Referer", "http://quote.eastmoney.com/");
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                for(int i=0; i<fields.length; i++){
                    System.out.println("00"+fields[i]);
                }
                //Double price = Double.parseDouble(fields[1]);
                //prices.add(price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }

    private static List<Double> calculateReturns(List<Double> prices) {
        List<Double> returns = new ArrayList<>();
        for (int i = 1; i < prices.size(); i++) {
            double currentPrice = prices.get(i);
            double prevPrice = prices.get(i - 1);
            double returnVal = (prevPrice - currentPrice) / prevPrice;
            returns.add(returnVal);
        }
        return returns;
    }

    private static double calculateMean(List<Double> returns) {
        double sum = 0.0;
        for (Double r : returns) {
            sum += r;
        }
        return sum / returns.size();
    }

    private static double calculateStdev(List<Double> returns, double mean) {
        double squaredDiffSum = 0.0;
        for (Double r : returns) {
            double diff = r - mean;
            squaredDiffSum += (diff * diff);
        }
        double variance = squaredDiffSum / (returns.size() - 1);
        return Math.sqrt(variance);
    }
}