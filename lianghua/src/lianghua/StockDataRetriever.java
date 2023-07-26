package lianghua;
import java.net.*;
import java.util.*;
import java.io.*;
import org.json.*;
import com.google.gson.*;

public class StockDataRetriever {
    private String username; // Wind/东方财富账户用户名
    private String password; // Wind/东方财富账户密码
    private String stockCode; // 股票代码

    public StockDataRetriever(String username, String password, String stockCode) {
        this.username = username;
        this.password = password;
        this.stockCode = stockCode;
    }

    public List<StockTradeData> retrieveDataFromWind() throws Exception {
        // 构建 Wind API 接口 URL
        String hostname = "http://api.waditu.com";
        String path = "/?";
        String method = "wsd";
        String token = ""; // Wind API 接口 token
        String fields = "open,close,high,low,amt,vol"; // 要获取的行情数据字段
        int startDate = 20220101; // 要获取的数据开始日期
        int endDate = 20220509; // 要获取的数据结束日期

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
            // 创建 JSON 解析器
            JSONParser parser = new JSONParser(data);

            // 解析 JSON 数据
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
            System.out.println("Wind API 请求失败，响应码：" + responseCode);
        }

        return result;
    }

    public List<StockTradeData> retrieveDataFromEastMoney() throws Exception {
        // 构建东方财富 API 接口 URL
        String hostname = "http://push2.eastmoney.com";
        String path = "/api/qt/kline/get";
        String stockType = "1"; // 沪深：1，港股：3，美股：6
        int startDate = 1640908800; // 要获取的数据开始时间戳
        int endDate = 1652179200; // 要获取的数据结束时间戳
        int interval = 86400; // 要获取的数据时间间隔，单位：秒

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
            // 创建 JSON 解析器
            JSONParser parser = new JSONParser(data);

            // 解析 JSON 数据
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
            System.out.println("东方财富 API 请求失败，响应码：" + responseCode);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        // 初始化股票交易数据查询参数
        String username = "your_username";
        String password = "your_password";
        String stockCode = "600290"; // 中国移动股票代码

        // 创建 StockDataRetriever 对象
        StockDataRetriever stockDataRetriever = new StockDataRetriever(username, password, stockCode);

        // 从 Wind 获取股票交易数据
        List<StockTradeData> windData = stockDataRetriever.retrieveDataFromWind();
        System.out.println("Wind 股票交易数据：");
        for (StockTradeData item : windData) {
            System.out.println(item.toString());
        }

        // 从东方财富获取股票交易数据
        List<StockTradeData> eastMoneyData = stockDataRetriever.retrieveDataFromEastMoney();
        System.out.println("东方财富股票交易数据：");
        for (StockTradeData item : eastMoneyData) {
            System.out.println(item.toString());
        }
    }
}