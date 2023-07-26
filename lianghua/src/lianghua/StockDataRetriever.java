package lianghua;
import java.net.*;
import java.util.*;
import java.io.*;
import org.json.*;
import com.google.gson.*;

public class StockDataRetriever {
    private String username; // Wind/�����Ƹ��˻��û���
    private String password; // Wind/�����Ƹ��˻�����
    private String stockCode; // ��Ʊ����

    public StockDataRetriever(String username, String password, String stockCode) {
        this.username = username;
        this.password = password;
        this.stockCode = stockCode;
    }

    public List<StockTradeData> retrieveDataFromWind() throws Exception {
        // ���� Wind API �ӿ� URL
        String hostname = "http://api.waditu.com";
        String path = "/?";
        String method = "wsd";
        String token = ""; // Wind API �ӿ� token
        String fields = "open,close,high,low,amt,vol"; // Ҫ��ȡ�����������ֶ�
        int startDate = 20220101; // Ҫ��ȡ�����ݿ�ʼ����
        int endDate = 20220509; // Ҫ��ȡ�����ݽ�������

        String urlStr = String.format("%s%sapi_name=%s&token=%s&fields=%s&start_date=%d&end_date=%d&param=[\"%s\"]",
                hostname, path, method, token, fields, startDate, endDate, stockCode);

        List<StockTradeData> result = new ArrayList<>();
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String data = response.toString();
            // ���� JSON ������
            JSONParser parser = new JSONParser(data);

            // ���� JSON ����
            JSONArray dataArray = parser.getJSONObject("data").getJSONArray("wsd").getJSONObject(0).getJSONArray("timeseries");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONArray item = dataArray.getJSONArray(i);
                double open = item.getDouble(0);
                double close = item.getDouble(1);
                double high = item.getDouble(2);
                double low = item.getDouble(3);
                double amt = item.getDouble(4);
                double vol = item.getDouble(5);
                int date = item.getInt(6);
                result.add(new StockTradeData(stockCode, date, open, high, low, close, amt, vol));
            }

        } else {
            System.out.println("Wind API ����ʧ�ܣ���Ӧ�룺" + responseCode);
        }

        return result;
    }

    public List<StockTradeData> retrieveDataFromEastMoney() throws Exception {
        // ���������Ƹ� API �ӿ� URL
        String hostname = "http://push2.eastmoney.com";
        String path = "/api/qt/kline/get";
        String stockType = "1"; // ���1���۹ɣ�3�����ɣ�6
        int startDate = 1640908800; // Ҫ��ȡ�����ݿ�ʼʱ���
        int endDate = 1652179200; // Ҫ��ȡ�����ݽ���ʱ���
        int interval = 86400; // Ҫ��ȡ������ʱ��������λ����

        String urlStr = String.format("%s%s?fields1=open,close,high,low,amount,volume&fqt=0&klt=101&secid=%s.%s&beg=%d&end=%d&_=%d",
                hostname, path, stockType, stockCode, startDate, endDate, System.currentTimeMillis());

        List<StockTradeData> result = new ArrayList<>();
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String data = response.toString();
            // ���� JSON ������
            JSONParser parser = new JSONParser(data);

            // ���� JSON ����
            JSONArray dataArray = parser.getJSONObject("data").getJSONArray("klines");
            for (int i = 0; i < dataArray.length(); i++) {
                String[] item = dataArray.getString(i).split(",");
                double open = Double.parseDouble(item[1]);
                double close = Double.parseDouble(item[2]);
                double high = Double.parseDouble(item[3]);
                double low = Double.parseDouble(item[4]);
                double amt = Double.parseDouble(item[5]);
                double vol = Double.parseDouble(item[6]);
                int date = Integer.parseInt(item[0])/1000;
                result.add(new StockTradeData(stockCode, date, open, high, low, close, amt, vol));
            }

        } else {
            System.out.println("�����Ƹ� API ����ʧ�ܣ���Ӧ�룺" + responseCode);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        // ��ʼ����Ʊ�������ݲ�ѯ����
        String username = "your_username";
        String password = "your_password";
        String stockCode = "600290"; // �й��ƶ���Ʊ����

        // ���� StockDataRetriever ����
        StockDataRetriever stockDataRetriever = new StockDataRetriever(username, password, stockCode);

        // �� Wind ��ȡ��Ʊ��������
        List<StockTradeData> windData = stockDataRetriever.retrieveDataFromWind();
        System.out.println("Wind ��Ʊ�������ݣ�");
        for (StockTradeData item : windData) {
            System.out.println(item.toString());
        }

        // �Ӷ����Ƹ���ȡ��Ʊ��������
        List<StockTradeData> eastMoneyData = stockDataRetriever.retrieveDataFromEastMoney();
        System.out.println("�����Ƹ���Ʊ�������ݣ�");
        for (StockTradeData item : eastMoneyData) {
            System.out.println(item.toString());
        }
    }
}