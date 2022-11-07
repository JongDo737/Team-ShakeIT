package com.example.shake.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CongressOfMember")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CongressOfMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(45)")
    private String HG_NM;   // 이름
    @Column(columnDefinition = "VARCHAR(45)")
    private String HJ_NM;       // 한자명
    @Column(columnDefinition = "VARCHAR(45)")
    private String ENG_NM;        //영문명칭
    @Column(columnDefinition = "VARCHAR(45)")
    private String BTH_GBN_NM;        //음/양력
    @Column(columnDefinition = "VARCHAR(45)")
    private String BTH_DATE;      // 생년월일
    @Column(columnDefinition = "VARCHAR(45)")
    private String img_URL;
    @Column(columnDefinition = "VARCHAR(45)")
    private String JOB_RES_NM;         // 직책명
    @Column(columnDefinition = "VARCHAR(45)")
    private String POLY_NM;      // 정당명
    @Column(columnDefinition = "VARCHAR(45)")
    private String ORIG_NM; //선거구
    @Column(columnDefinition = "VARCHAR(45)")
    private String ELECT_GBN_NM;  // 선거구구분
    @Column(columnDefinition = "MEDIUMTEXT")
    private String CMIT_NM; //대표 위원회
    @Column(columnDefinition = "MEDIUMTEXT")
    private String CMITS; // 소속 위원회 목록
    @Column(columnDefinition = "VARCHAR(45)")
    private String REELE_GBN_NM; // 재선
    @Column(columnDefinition = "VARCHAR(45)")
    private String UNITS;        // 당선
    @Column(columnDefinition = "VARCHAR(45)")
    private String SEX_GBN_NM;        // 성별
    @Column(columnDefinition = "VARCHAR(45)")
    private String TEL_NO;        // 전화번호
    @Column(columnDefinition = "VARCHAR(45)")
    private String E_MAIL;        // 이메일
    @Column(columnDefinition = "VARCHAR(45)")
    private String HOMEPAGE;        // 홈페이지
    @Column(columnDefinition = "VARCHAR(45)")
    private String STAFF;        // 보좌관
    @Column(columnDefinition = "VARCHAR(45)")
    private String SECRETARY;        // 비서관
    @Column(columnDefinition = "VARCHAR(45)")
    private String SECRETARY2;        // 비서
    @Column(columnDefinition = "VARCHAR(45)")
    private String MONACD;        // 국회의원코드
    @Column(columnDefinition = "MEDIUMTEXT")
    private String MEM_TITLE;        // 약력
    @Column(columnDefinition = "VARCHAR(45)")
    private String ASSEM_ADDR;        // 사무실 호실
    @Column(columnDefinition = "VARCHAR(45)")
    private String create_date;        // 생성날짜
    @Column(columnDefinition = "VARCHAR(45)")
    private String update_date;        // 수정날짜
}
