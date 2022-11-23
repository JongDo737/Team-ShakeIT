package com.example.shake.entity;

import com.example.shake.api.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "Calendar")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(columnDefinition = "MEDIUMTEXT")
    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String committee_name;

    @Column(columnDefinition = "VARCHAR(45)")
    private String date;

    @Column(columnDefinition = "VARCHAR(45)")
    private String time;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String url;

    public Notification toNotification(){
        return Notification.builder()
                .title(title)
                .code("2")
                .createdate(Date.getDate())
                .build();
    }
}

