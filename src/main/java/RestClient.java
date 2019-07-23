import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    public void get(String uri) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri); //http req
        //HTTP Status code
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet); //hit the API and get response
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code >>>> " + statusCode);
        //Response Headers
        Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> getHeaders = new HashMap<String, String>();
        for (Header header : headers) {
            getHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Total headers >>>>> " + getHeaders.size());
        System.out.println("Response headers >>>>> " + getHeaders);
        System.out.println("Specific headers >>>>> " + closeableHttpResponse.getFirstHeader("Cache-Control").toString());
        //Response JSON
        String httpEntity = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("http entity or response json>>>> " + new JSONObject(httpEntity));

    }
}
