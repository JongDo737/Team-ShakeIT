package com.example.shake.dto;

import com.example.shake.entity.ProcessedPetition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessedPetitionDto {
    int id;

    String bill_id;//BILL_ID
    String num;//BILL_NO
    String age;//AGE
    String name;//BILL_NAME
    String proposer;//PROPOSER
    String approver;//APPROVER
    String pro_dt;//PROPOSE_DT
    String pro_result;//PROC_RESULT_CD
    String committee_id;//CURR_COMMITTEE_ID
    String curr_committee;//CURR_COMMITTEE
    String url;//LINK_URL
    String committee_dt; //COMMITTEE_DT
    public ProcessedPetition toEntity(){
        return ProcessedPetition.builder()
                .bill_id(bill_id)
                .num(num)
                .age(age)
                .name(name)
                .proposer(proposer)
                .approver(approver)
                .pro_dt(pro_dt)
                .pro_result(pro_result)
                .committee_id(committee_id)
                .committee_dt(committee_dt)
                .curr_committee(curr_committee)
                .url(url)
                .build();
    }
}
