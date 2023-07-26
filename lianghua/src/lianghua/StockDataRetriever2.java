package lianghua;
import java.net.*;
import java.util.*;
import java.io.*;
import org.json.*;
import com.google.gson.*;


public class StockDataRetriever2 {
    private String username; // 东方财富账户用户名
    private String password; // 东方财富账户密码
    private String stockCode; // 股票代码

    private String name;
    private double price;

    public StockDataRetriever2(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    
    public StockDataRetriever2(String username, String password, String stockCode) {
        this.username = username;
        this.password = password;
        this.stockCode = stockCode;
    }

    public List<StockDataRetriever2> retrieveData() throws Exception {
        // 构建东方财富 API 接口 URL
        String hostname = "http://push2.eastmoney.com";
        String path = "/api/qt/stock/get";
        String stockType = "1"; // 沪深：1，港股：5，美股：2
        String fields = "f51,f52,f53,f54,f100,f152"; // 要获取的数据字段
        String fs = "m:0+t:6,m:0+t:13"; // 要获取的数据类型，m:0+t:6 表示日 K 线，m:0+t:13 表示资金流向

        String urlStr = String.format("%s%s?fields=%s&ut=fa5fd1943c7b386f172d6893dbfba10b&fltt=2&secid=%s.%s&fs=%s&p=1&pn=100&_=%d",
                hostname, path, fields, stockType, stockCode, fs, System.currentTimeMillis());

        List<StockData> result = new ArrayList<>();
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
            JSONObject rawData = parser.getJSONObject("data").getJSONObject("f52").getJSONObject("data");
            JSONArray timeArray = rawData.getJSONArray("t");
            JSONArray dataArray = rawData.getJSONArray("v");
            for (int i = 0; i < timeArray.length(); i++) {
                long timestamp = timeArray.getLong(i) * 1000; // 转换成毫秒级时间戳
                double open = dataArray.getJSONArray(i).getDouble(0);
                double close = dataArray.getJSONArray(i).getDouble(1);
                double high = dataArray.getJSONArray(i).getDouble(2);
                double low = dataArray.getJSONArray(i).getDouble(3);
                double vol = dataArray.getJSONArray(i).getDouble(4);
                double amount = dataArray.getJSONArray(i).getDouble(5);
                result.add(new StockData(stockCode, new Date(timestamp), open, high, low, close, vol, amount));
            }

        } else {
            System.out.println("东方财富 API 请求失败，响应码：" + responseCode);
        }

        return result;
    }
}