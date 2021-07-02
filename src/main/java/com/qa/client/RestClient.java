package com.qa.client;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    //1. Get Method
    public void get(String url) {
        int statusCode = 0;
        String response = null;
        System.out.println("URL: "+url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = null;


        try {
            closeableHttpResponse = httpClient.execute(httpGet);
            statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            response = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* System.out.println("Status code: " + statusCode);
        System.out.println("Response: "+response);*/
        assert response != null;
        JSONObject responseJson = new JSONObject(response);
        System.out.println("Status code: " + statusCode);
        System.out.println("Response: "+response);
        System.out.println("ResponseJson: "+responseJson);
        Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String,String> allHeaders = new HashMap<String,String>();

        for(Header header:headers){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: "+allHeaders);
    }
}
