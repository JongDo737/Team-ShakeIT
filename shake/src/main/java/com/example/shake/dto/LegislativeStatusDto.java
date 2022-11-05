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
    String bill_id;//BILL_ID
    String bill_no;//BILL_NO
    String bill_name;//BILL_NAME
    String age;//AGE
    String pro_kind;//PROPOSER_KIND_CD
    String curr_committee;//CURR_COMMITTEE
    String noti_end_dt;//NOTI_ED_DT
    String link;//LINK_URL
    String proposer;//PROPOSER

    String committee_id;//CURR_COMMITTEE_ID
}
