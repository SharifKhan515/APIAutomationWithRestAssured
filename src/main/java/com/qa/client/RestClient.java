package com.qa.client;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    //1. Get Method
    public CloseableHttpResponse get(String url) {
        System.out.println("URL: " + url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return closeableHttpResponse;

    }

    // Get with header
    public CloseableHttpResponse get(String url, HashMap<String, String> header) {
        CloseableHttpResponse closeableHttpResponse = null;
        System.out.println("URL: " + url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }

        try {
            closeableHttpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return closeableHttpResponse;

    }
}
