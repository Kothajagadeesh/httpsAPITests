import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    //get request without headers
    public CloseableHttpResponse get(String uri) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri); //http req
        //HTTP Status code
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet); //hit the API and get response
        return closeableHttpResponse;
    }

    //get request with headers
    public CloseableHttpResponse getWithHeader(String uri, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri); //http req
        if (headers.size() != 0) { //checking headers size if not zero
            for (Map.Entry<String, String> header : headers.entrySet()) { //loop all the headers
                httpGet.addHeader(header.getKey(), header.getValue()); //add the headers to entry
            }
        }
        //HTTP Status code
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);//hit the API and get response
        return closeableHttpResponse;
    }
}
