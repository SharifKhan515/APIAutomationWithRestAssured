package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetAPITest extends TestBase {

    String url = null;
    TestBase testBase;
    RestClient restClient;
    @BeforeClass
    public void initialize(){
        testBase = new TestBase();
       String  siteUrl = prop.getProperty("URL");
       String apiUrl = prop.getProperty("serviceURL");
       /*System.out.println("site: "+siteUrl);
        System.out.println("API: "+apiUrl);*/
       url = siteUrl+apiUrl;
    }

    @Test()
    public void getAPITest(){
        restClient = new RestClient();
        restClient.get(url);
    }
}
