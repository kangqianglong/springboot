package lianghua;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/*（1）选股：获得营业总收入前100的股票，市盈率大于50并且小于65的，

（2）调仓：每日调仓，将所有资金平均到这10个股票的购买策略，一次性卖出所有不符合条件的*/

public class StockDataFetcher {

    // 定义东方财富 API 的基础 URL
    private static final String BASE_URL = "http://api.finance.ifeng.com/akdaily/?symbol=%s&type=last";

    // 获取股票数据
    public static JSONObject fetch(String symbol) {
        try {
            // 构造 API 请求 URL
            String url = String.format(BASE_URL, symbol);

            // 发送 HTTP GET 请求
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");

            // 读取 API 返回数据并转换成 JSON 对象
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) throws Exception {
    	JSONObject data = StockDataFetcher.fetch("SH000001");
    	System.out.println(data);	
    }
}