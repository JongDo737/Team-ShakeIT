package com.example.shake.api;

import java.time.LocalDate;

public class Date {
    public static String getDate() {

        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
        LocalDate now = LocalDate.now();
        // 결과 출력
        System.out.println(now);      // 2021-06-17

        return now+"";
    }
}
