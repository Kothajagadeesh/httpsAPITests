import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
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

    //post method
    public CloseableHttpResponse postRequest(String uri, String jsonPayLoad, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri); //Http post request
        httpPost.setEntity(new StringEntity(jsonPayLoad)); //body/payload
        if (headers.size() != 0) { //checking headers size if not zero
            for (Map.Entry<String, String> header : headers.entrySet()) { //loop all the headers
                httpPost.addHeader(header.getKey(), header.getValue()); //add the headers to entry
            }
        }
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        return closeableHttpResponse;
    }

    //post method
    public CloseableHttpResponse putRequest(String uri, String jsonPayLoad, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(uri); //Http post request
        httpPut.setEntity(new StringEntity(jsonPayLoad)); //body/payload
        if (headers.size() != 0) { //checking headers size if not zero
            for (Map.Entry<String, String> header : headers.entrySet()) { //loop all the headers
                httpPut.addHeader(header.getKey(), header.getValue()); //add the headers to entry
            }
        }
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPut);
        return closeableHttpResponse;
    }

    //delete method
    public CloseableHttpResponse deleteRequest(String uri) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(uri);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpDelete);
        return closeableHttpResponse;
    }
}


