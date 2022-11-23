package com.example.shake.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Notification")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private int code;
    // 1 : 종료 입법
    // 2 : 일
    // 3 : 진행 입법
    // 4 : 진행중 청원
    // 5 : 만료 청원

    @Column(columnDefinition = "MEDIUMTEXT")
    private String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String url;

    @Column
    private Date createdate;
}
