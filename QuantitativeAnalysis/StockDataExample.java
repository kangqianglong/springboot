import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class StockDataExample {
    public static void main(String[] args) throws Exception {
        // create HTTP client
        HttpClient httpClient = HttpClientBuilder.create().build();

        // make API request to Alpha Vantage
        HttpGet request = new HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&apikey=demo");
        HttpResponse response = httpClient.execute(request);

        // parse API response
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
    }
}