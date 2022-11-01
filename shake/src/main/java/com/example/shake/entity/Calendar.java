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
    private int type;

    @Column(columnDefinition = "VARCHAR(45)")
    private String session;

    @Column(columnDefinition = "VARCHAR(45)")
    private String title;

    @Column(columnDefinition = "VARCHAR(45)")
    private String date;

    @Column(columnDefinition = "VARCHAR(45)")
    private String time;

    @Column(columnDefinition = "VARCHAR(45)")
    private String daesu;
}
