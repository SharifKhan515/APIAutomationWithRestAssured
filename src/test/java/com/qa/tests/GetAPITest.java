package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class GetAPITest extends TestBase {

    String url = null;
    RestClient restClient;

    @BeforeClass
    public void initialize() {
        String siteUrl = prop.getProperty("URL");
        String apiUrl = prop.getProperty("serviceURL");
       /*System.out.println("site: "+siteUrl);
        System.out.println("API: "+apiUrl);*/
        url = siteUrl + apiUrl;
    }

    @Test()
    public void getAPITest() {
        CloseableHttpResponse closeableHttpResponse;
        String response = null;
        int statusCode = 0;
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        try {
            response = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not Matched");

        JSONObject responseJson = new JSONObject(response);
        System.out.println("Response: "+response);
        System.out.println("ResponseJson: "+responseJson);
        Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String,String> allHeaders = new HashMap<String,String>();

        for(Header header:headers){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: "+allHeaders);

    }
    @Test()
    public void getAPITestWithHeader() {
        CloseableHttpResponse closeableHttpResponse;
        String response = null;
        int statusCode = 0;
        restClient = new RestClient();
        HashMap<String,String> header = new HashMap<String,String>();
        header.put("Content-Type","application/json");
        closeableHttpResponse = restClient.get(url,header);
        try {
            response = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not Matched");

        JSONObject responseJson = new JSONObject(response);
        System.out.println("Response: "+response);
        System.out.println("ResponseJson: "+responseJson);
      /*  Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String,String> allHeaders = new HashMap<String,String>();

        for(Header header:headers){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: "+allHeaders);
*/
    }
}
