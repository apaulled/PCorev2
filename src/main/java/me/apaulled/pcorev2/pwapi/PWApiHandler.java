package me.apaulled.pcorev2.pwapi;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PWApiHandler {

    private String responseString;

    public PWApiHandler(String urlString) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(urlString);

        HttpResponse httpResponse = httpClient.execute(httpGet);

        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        this.responseString = sc.nextLine();
    }

    public String getResponseString() {
        return this.responseString;
    }
}
