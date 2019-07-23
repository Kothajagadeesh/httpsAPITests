import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class getApiTest extends BaseTestApi {

    String url, pathParameter, uri;

    @BeforeMethod
    public void setUp() {
        init();
        url = properties.getProperty("url");
        pathParameter = properties.getProperty("pathParameter");
        uri = url + pathParameter;
    }

    @Test
    public void getTest() throws IOException {
        RestClient restClient = new RestClient();
        restClient.get(uri);
    }
}
