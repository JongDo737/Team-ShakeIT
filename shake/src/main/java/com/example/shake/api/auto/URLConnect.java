package com.example.shake.api.auto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLConnect {
    public static void go() throws IOException {
        URL url = new URL("https://1485-164-125-221-236.jp.ngrok.io/getBill");
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
}
