import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.StatusCodes;

import java.io.IOException;
import java.util.HashMap;

public class deleteApiTest extends BaseTestApi {

    String uri, url, pathParameter;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        loadPropertiesFile();
        url = properties.getProperty("url");
        pathParameter = properties.getProperty("pathParameter") + properties.getProperty("pathParameterUser");
        uri = url + pathParameter;
    }

    @Test
    public void deleteApiTests() throws IOException {
        RestClient restClient = new RestClient();
        closeableHttpResponse = restClient.deleteRequest(uri);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        String protocol = closeableHttpResponse.getStatusLine().getProtocolVersion().getProtocol();
        String statusMessage = closeableHttpResponse.getStatusLine().getReasonPhrase();
        System.out.println("Status code >>>" + statusCode);
        System.out.println("protocol >>>" + protocol);
        System.out.println("reason >>>" + statusMessage);
        Assert.assertEquals(statusCode, StatusCodes.NO_CONTENT);
        //Response JSON
        // no json will be displayed
        //Response Headers
        Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> getHeaders = new HashMap<String, String>();
        for (Header header : headers) {
            getHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Response headers >>>>> " + getHeaders);
    }
}
