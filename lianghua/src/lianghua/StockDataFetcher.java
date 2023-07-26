package lianghua;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/*��1��ѡ�ɣ����Ӫҵ������ǰ100�Ĺ�Ʊ����ӯ�ʴ���50����С��65�ģ�

��2�����֣�ÿ�յ��֣��������ʽ�ƽ������10����Ʊ�Ĺ�����ԣ�һ�����������в�����������*/

public class StockDataFetcher {

    // ���嶫���Ƹ� API �Ļ��� URL
    private static final String BASE_URL = "http://api.finance.ifeng.com/akdaily/?symbol=%s&type=last";

    // ��ȡ��Ʊ����
    public static JSONObject fetch(String symbol) {
        try {
            // ���� API ���� URL
            String url = String.format(BASE_URL, symbol);

            // ���� HTTP GET ����
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");

            // ��ȡ API �������ݲ�ת���� JSON ����
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