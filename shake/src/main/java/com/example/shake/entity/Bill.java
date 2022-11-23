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
@Table(name = "Bill")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "VARCHAR(45)")
    String daesu;
    @Column(columnDefinition = "VARCHAR(45)")
    String bill_num;
    @Column(columnDefinition = "MEDIUMTEXT")
    String bill_name;
    @Column(columnDefinition = "VARCHAR(45)")
    String propersor;
    @Column(columnDefinition = "VARCHAR(45)")
    String committee_nm;
    @Column(columnDefinition = "VARCHAR(45)")
    String proc_result;
    @Column(columnDefinition = "VARCHAR(45)")
    String proc_date;
    @Column(columnDefinition = "VARCHAR(45)")
    String curr_trans_dt;
    @Column(columnDefinition = "VARCHAR(45)")
    String announce_dt;
    @Column(columnDefinition = "MEDIUMTEXT")
    String url;
    @Column(columnDefinition = "MEDIUMTEXT")
    String rgs_proc_dt;
    @Column(columnDefinition = "MEDIUMTEXT")
    String billid;
    @Column(columnDefinition = "MEDIUMTEXT")
    String curr_committee_id;
    @Column(columnDefinition = "VARCHAR(45)")
    private String create_date;        // 생성날짜
    @Column(columnDefinition = "VARCHAR(45)")
    private String update_date;        // 수정날짜

    public Notification toNotification(){
        return Notification.builder()
                .title(bill_name)
                .code("1")
                .createdate(Date.getDate())
                .build();
    }
}
