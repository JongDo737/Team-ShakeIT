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
@Table(name = "pending_petition")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingPetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "VARCHAR(45)")
    String num;
    @Column(columnDefinition = "VARCHAR(45)")
    String age;
    @Column(columnDefinition = "MEDIUMTEXT")
    String name;
    @Column(columnDefinition = "VARCHAR(45)")
    String proposer;
    @Column(columnDefinition = "VARCHAR(45)")
    String approver;
    @Column(columnDefinition = "VARCHAR(45)")
    String pro_dt;
    @Column(columnDefinition = "VARCHAR(45)")
    String curr_committee;
    @Column(columnDefinition = "MEDIUMTEXT")
    String url;
    @Column(columnDefinition = "MEDIUMTEXT")
    String billid;
    @Column(columnDefinition = "VARCHAR(45)")
    String committee_id;
    @Column(columnDefinition = "VARCHAR(45)")
    String curr_committee_dt;
    @Column(columnDefinition = "VARCHAR(45)")
    private String create_date;        // 생성날짜
    @Column(columnDefinition = "VARCHAR(45)")
    private String update_date;        // 수정날짜
    public Notification toNotification(){
        return Notification.builder()
                .title(name)
                .code("4")
                .createdate(Date.getDate())
                .build();
    }
}
