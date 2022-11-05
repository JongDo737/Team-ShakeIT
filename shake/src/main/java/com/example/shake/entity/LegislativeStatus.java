package com.example.shake.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    String bill_id;
    @Column(columnDefinition = "VARCHAR(45)")
    String committee_id;

}
