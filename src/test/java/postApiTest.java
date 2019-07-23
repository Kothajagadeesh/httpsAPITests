import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.StatusCodes;

import java.util.HashMap;

public class postApiTest extends BaseTestApi {

    String url, pathParameter, uri;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        init();
        url = properties.getProperty("url");
        pathParameter = properties.getProperty("pathParameter");
        uri = url + pathParameter;
    }

    @Test
    public void postApiTests() throws Exception {
        RestClient restClient = new RestClient();
        HashMap<String, String> req_headers = new HashMap<String, String>();
        req_headers.put("Content-Type", "application/json");
        String jsonFile = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";
        closeableHttpResponse = restClient.postRequest(uri, jsonFile, req_headers);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, StatusCodes.STATUS_CREATED);

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
