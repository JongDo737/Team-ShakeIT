package com.example.shake.api.auto;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class URLConnect {

    public static void go() throws IOException {
        URL url = new URL("http://52.79.155.82:8080/updateDB");
        // HTTP Connection 구하기
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        String API_URL = "http://52.79.155.82:8080/updateDB";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());


    }
    public static void updateBill() throws IOException {
        String API_URL = "http://52.79.155.82:8080/getMessageList";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
    public static void sendMessage(String token, String title, String body) throws IOException {
        String API_URL = "http://52.79.155.82:8080/sendPushMsg/"+token+"/"+title+"/"+body;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                        .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
}
