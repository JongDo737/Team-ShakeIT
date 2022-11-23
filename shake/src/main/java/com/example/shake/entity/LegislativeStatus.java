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
@Table(name = "legislativeStatus")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegislativeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "VARCHAR(45)")
    String bill_no;
    @Column(columnDefinition = "MEDIUMTEXT")
    String bill_name;
    @Column(columnDefinition = "VARCHAR(45)")
    String age;
    @Column(columnDefinition = "VARCHAR(45)")
    String pro_kind;
    @Column(columnDefinition = "VARCHAR(45)")
    String curr_committee;
    @Column(columnDefinition = "VARCHAR(45)")
    String noti_end_dt;
    @Column(columnDefinition = "MEDIUMTEXT")
    String link;
    @Column(columnDefinition = "VARCHAR(45)")
    String proposer;
    @Column(columnDefinition = "MEDIUMTEXT")
    String billid;
    @Column(columnDefinition = "VARCHAR(45)")
    String committee_id;

    public Notification toNotification(){
        return Notification.builder()
                .title(bill_name)
                .code("3")
                .createdate(Date.getDate())
                .build();
    }
}
