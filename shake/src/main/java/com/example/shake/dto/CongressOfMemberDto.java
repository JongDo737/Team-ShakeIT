package com.example.shake.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongressOfMemberDto {
    private Long id;
    private String daesu;   // 몇 대 국회의원
    private String party;       // 정당
    private String name;        //이름
    private String pictureURL;        //사진 정보
    private String region;      // 선거구
    private String sex;         // 성별
    private String birth;      // 생일
    private String re_election; //재선
    private String elected;  // 당선
    private String elect_gbn_nm; //선거구 구분
    private String eng_name; // 영어이름
    private String cha_name; // 한자이름
    private String create_date;        // 생성날짜
    private String update_date;        // 수정날짜

}
