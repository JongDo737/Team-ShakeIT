package com.example.shake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDto {
    int id;
    String daesu;
    String bill_num;
    String bill_name;
    String propersor;
    String committee_nm;
    String proc_result;
    String proc_date;
    String curr_trans_dt;
    String announce_dt;
    String url;
    String rgs_proc_dt;
    String bill_id;
    String curr_committee_id;
}
