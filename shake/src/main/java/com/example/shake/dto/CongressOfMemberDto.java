package com.example.shake.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongressOfMemberDto {
    private Long id;
    private String HG_NM;   // 이름
    private String HJ_NM;       // 한자명
    private String ENG_NM;        //영문명칭
    private String BTH_GBN_NM;        //음/양력
    private String BTH_DATE;      // 생년월일
    private String img_URL;
    private String JOB_RES_NM;         // 직책명
    private String POLY_NM;      // 정당명
    private String ORIG_NM; //선거구
    private String ELECT_GBN_NM;  // 선거구구분
    private String CMIT_NM; //대표 위원회
    private String CMITS; // 소속 위원회 목록
    private String REELE_GBN_NM; // 재선
    private String UNITS;        // 당선
    private String SEX_GBN_NM;        // 성별
    private String TEL_NO;        // 전화번호
    private String E_MAIL;        // 이메일
    private String HOMEPAGE;        // 홈페이지
    private String STAFF;        // 보좌관
    private String SECRETARY;        // 비서관
    private String SECRETARY2;        // 비서
    private String MONA_CD;        // 국회의원코드
    private String MEM_TITLE;        // 약력
    private String ASSEM_ADDR;        // 사무실 호실
    private String create_date;        // 생성날짜
    private String update_date;        // 수정날짜

}
