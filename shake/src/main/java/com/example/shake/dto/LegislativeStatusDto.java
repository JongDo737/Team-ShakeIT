package com.example.shake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LegislativeStatusDto {
    int id;
    String bill_no;
    String bill_name;
    String age;
    String pro_kind;
    String curr_committee;
    String noti_end_dt;
    String link;
    String proposer;
    String bill_id;
    String committee_id;
}
