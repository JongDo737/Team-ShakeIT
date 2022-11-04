package com.example.shake.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "Calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int code;
    // 1 : 국회의원 세미나
    // 2 : 본회의 일정
    // 3 : 위원회 별 일정
    // 4 : 국회의장 일정
    // 5 : 공청회 일정

    @Column(columnDefinition = "VARCHAR(45)")
    private String title;
    @Column(columnDefinition = "VARCHAR(45)")
    private String committee_name;

    @Column(columnDefinition = "VARCHAR(45)")
    private String date;

    @Column(columnDefinition = "VARCHAR(45)")
    private String time;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String url;

}
