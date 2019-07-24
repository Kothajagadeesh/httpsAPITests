import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.StatusCodes;

import java.io.IOException;
import java.util.HashMap;

public class putApiTest extends BaseTestApi {
    String uri, url, pathUrl;
    CloseableHttpResponse closeableHttpResponse;
    RestClient restClient;

    @BeforeMethod
    public void setup() {
        loadPropertiesFile();
        url = properties.getProperty("url");
        pathUrl = properties.getProperty("pathParameter") + properties.getProperty("pathParameterUser");
        uri = url + pathUrl;
    }

    @Test
    public void putApiTests() throws IOException {
        RestClient restClient = new RestClient();
        HashMap<String, String> req_headers = new HashMap<String, String>();
        req_headers.put("Content-Type", "application/json");
        String jsonFile = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        closeableHttpResponse = restClient.putRequest(uri, jsonFile, req_headers);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, StatusCodes.STATUS_OK, "Expecting 200");
        String json = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("JSON RESPONSE >>>>> " + json);

        Header[] res_headers = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> getHeaders = new HashMap<String, String>();
        for (Header header : res_headers) {
            getHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Response headers >>>>> " + getHeaders);
    }


}
