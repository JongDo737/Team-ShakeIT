package com.example.shake.api;

import java.time.LocalDate;
import java.util.List;

public class Date {
    public static String getDate() {

        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
        LocalDate now = LocalDate.now();
        // 결과 출력
        System.out.println(now);      // 2021-06-17

        return now+"";
    }
    public static String getYearMonth(){
        LocalDate now = LocalDate.now();
        System.out.println("오늘 날짜 : "+now.getYear()+"-"+(now.getMonthValue()));
        return now.getYear()+"-"+(now.getMonthValue()-1);
    }

}
