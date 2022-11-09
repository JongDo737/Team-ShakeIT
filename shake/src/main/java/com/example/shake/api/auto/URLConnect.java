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
        URL url = new URL("http://3.35.187.40:8080/updateDB");
        // HTTP Connection 구하기
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
        conn.setRequestMethod("GET");

        // 연결 타임아웃 설정
        conn.setConnectTimeout(3000); // 3초
        // 읽기 타임아웃 설정
        conn.setReadTimeout(3000); // 3초

        // 응답 메시지 구하기
        System.out.println("getResponseMessage():" + conn.getResponseMessage());


    }
    public static void updateBill() throws IOException {
        String API_URL = "http://3.35.187.40:8080/getMessageList";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
    public static void sendMessage(String token, String title, String body) throws IOException {
        String API_URL = "http://3.35.187.40:8080/sendPushMsg/"+token+"/"+title+"/"+body;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                        .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
}
