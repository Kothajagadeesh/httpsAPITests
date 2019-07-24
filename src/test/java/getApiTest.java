import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.StatusCodes;

import java.io.IOException;
import java.util.HashMap;

public class getApiTest extends BaseTestApi {

    String url, pathParameter, uri;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        loadPropertiesFile();
        url = properties.getProperty("url");
        pathParameter = properties.getProperty("pathParameter");
        uri = url + pathParameter;
    }

    @Test
    public void getApiTest() throws IOException {
        RestClient restClient = new RestClient();
        closeableHttpResponse = restClient.get(uri);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code >>>> " + statusCode);
        Assert.assertEquals(StatusCodes.STATUS_OK, statusCode, "status code not " + StatusCodes.STATUS_OK);
        //Response JSON
        String httpEntity = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("http entity or response json>>>> " + new JSONObject(httpEntity));
        //Response Headers
        Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> getHeaders = new HashMap<String, String>();
        for (Header header : headers) {
            getHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Response headers >>>>> " + getHeaders);
    }

    @Test
    public void getApiTestWithHeaders() throws IOException {
        RestClient restClient = new RestClient();
        HashMap<String, String> req_headers = new HashMap<String, String>();
        req_headers.put("Content-Type", "application/json");
        req_headers.put("username", "test@yopmail.com"); //dummy headers
        req_headers.put("password", "123456"); //dummy headers
        closeableHttpResponse = restClient.getWithHeader(uri, req_headers); //adding headers
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code >>>> " + statusCode);
        Assert.assertEquals(StatusCodes.STATUS_OK, statusCode, "status code not " + StatusCodes.STATUS_OK);
        //Response JSON
        String httpEntity = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("http entity or response json>>>> " + new JSONObject(httpEntity));
        //Response Headers
        Header[] res_headers = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> getHeaders = new HashMap<String, String>();
        for (Header header : res_headers) {
            getHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Response headers >>>>> " + getHeaders);
    }
}
