package com.example.shake.dto;

import com.example.shake.entity.LegislativeStatus;
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
    public LegislativeStatus toEntity(){
        return LegislativeStatus.builder()
                .bill_id(bill_id)
                .bill_no(bill_no)
                .bill_name(bill_name)
                .age(age)
                .pro_kind(pro_kind)
                .curr_committee(curr_committee)
                .noti_end_dt(noti_end_dt)
                .link(link)
                .proposer(proposer)
                .committee_id(committee_id)
                .build();
    }
}
