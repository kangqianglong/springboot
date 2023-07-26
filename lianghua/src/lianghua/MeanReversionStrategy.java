package lianghua;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.methods.HttpGet;

public class MeanReversionStrategy {

    private static final String STOCK_CODE = "AAPL"; // 股票代码
    private static final int LOOKBACK_PERIOD = 20; // 回看周期
    private static final double MEAN = 0.0; // 均值
    private static final double STDEV = 1.0; // 标准差
    private static double cash = 100000.0; // 初始资金

    public static void main(String[] args) {
        List<Double> prices = readStockData();
        List<Double> returns = calculateReturns(prices);
        double mean = calculateMean(returns);
        double stdev = calculateStdev(returns, mean);
        double lowerThreshold = MEAN - STDEV;
        double upperThreshold = MEAN + STDEV;

        System.out.println("均值 " + mean);
        System.out.println("标准差 " + stdev);

        double position = 0.0;

        for (int i = LOOKBACK_PERIOD; i < prices.size(); i++) {
            double currentPrice = prices.get(i);
            double prevPrice = prices.get(i - LOOKBACK_PERIOD);
            double previousReturn = (prevPrice - currentPrice) / prevPrice;
            double currentReturn = (prices.get(i - 1) - currentPrice) / prices.get(i - 1);
            double diff = mean - previousReturn;
            boolean inRange = (previousReturn >= lowerThreshold && previousReturn <= upperThreshold);

            if (inRange && diff > 0) {
                // 价格在范围之内且下降，买入
                double buyPrice = currentPrice;
                int units = (int) (cash / buyPrice);
                position += units;
                cash -= units * buyPrice;
                System.out.println("[" + i + "] 买入股票 " + units + " 股 @ " + buyPrice + " 总金额 " + (units * buyPrice) + " 剩余现金 " + cash);

            } else if (!inRange || diff < 0) {
                // 价格在范围之外或上升，卖出
                double sellPrice = currentPrice;
                cash += position * sellPrice;
                System.out.println("[" + i + "] 卖出股票 " + position + " 股 @ " + sellPrice + " 总金额 " + (position * sellPrice) + " 剩余现金 " + cash);
                position = 0.0;
            }
        }
        
        // 计算回测结果
        double pnl = cash + position * prices.get(prices.size() - 1);
        double roi = (pnl - 100000) / 100000;
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.println("回测结束，最终手中持股数: " + position + " 持有股票价值: " + position * prices.get(prices.size() - 1) + " 当前总资产: " + pnl + " 收益率: " + df.format(roi*100) + "%");
    }

    private static List<Double> readStockData() {
        List<Double> prices = new ArrayList<>();
        try {
            String url = "https://finance.yahoo.com/quote/" + STOCK_CODE + "/history/";
           // HttpGet request = new HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&apikey=demo");

            URL yahooFinance = new URL(url);
            URLConnection connection = yahooFinance.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("</span>")) {
                    String raw = inputLine.substring(inputLine.lastIndexOf("</span>") + 7);
                    if (raw.startsWith("</td><td class")) {
                        int end = raw.indexOf("</td>");
                        raw = raw.substring(19, end);
                        double price = Double.parseDouble(raw.replaceAll(",", ""));
                        prices.add(price);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }

    private static List<Double> calculateReturns(List<Double> prices) {
        List<Double> returns = new ArrayList<>();
        for (int i = 1; i < prices.size(); i++) {
            returns.add((prices.get(i - 1) - prices.get(i)) / prices.get(i - 1));
        }
        return returns;
    }

    private static double calculateMean(List<Double> returns) {
        double sum = 0.0;
        for (double r : returns) {
            sum += r;
        }
        return sum / returns.size();
    }

    private static double calculateStdev(List<Double> returns, double mean) {
        double sum = 0.0;
        for (double r : returns) {
            sum += (r - mean) * (r - mean);
        }
        return Math.sqrt(sum / (returns.size() - 1));
    }
}