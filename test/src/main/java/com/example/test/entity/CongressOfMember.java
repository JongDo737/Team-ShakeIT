package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CongressOfMember")
public class CongressOfMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(45)")
    private String daesu;   // 몇 대 국회의원

    @Column(columnDefinition = "VARCHAR(45)")
    private String party;       // 정당

    @Column(columnDefinition = "VARCHAR(45)")
    private String name;        //이름

    @Column(columnDefinition = "VARCHAR(45)")
    private String pictureURL;        //사진 정보

    @Column(columnDefinition = "VARCHAR(45)")
    private String region;      // 선거구

    @Column(columnDefinition = "VARCHAR(45)")
    private String sex;         // 성별

    @Column(columnDefinition = "VARCHAR(45)")
    private String birth;      // 생일

    @Column(columnDefinition = "VARCHAR(45)")
    private String re_election; //재선

    @Column(columnDefinition = "VARCHAR(45)")
    private String elected;  // 당선

    @Column(columnDefinition = "VARCHAR(45)")
    private String elect_gbn_nm; //선거구 구분

    @Column(columnDefinition = "VARCHAR(45)")
    private String eng_name; // 영어이름

    @Column(columnDefinition = "VARCHAR(45)")
    private String cha_name; // 한자이름

    @Column(columnDefinition = "VARCHAR(45)")
    private String create_date;        // 생성날짜

    @Column(columnDefinition = "VARCHAR(45)")
    private String update_date;        // 수정날짜
}
