package com.example.shake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarDto {
    private Long id;

    private int code;
    // 1 : 국회의원 세미나
    // 2 : 본회의 일정
    // 3 : 위원회 별 일정
    // 4 : 국회의장 일정
    // 5 : 공청회 일정

    private String title;
    private String committee_name;

    private String date;

    private String time;

    private String url;
}
