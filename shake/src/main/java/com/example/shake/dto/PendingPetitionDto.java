package com.example.shake.dto;

import com.example.shake.entity.PendingPetition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PendingPetitionDto {

    int id;

    String bill_id;//BILL_ID
    String num;//BILL_NO
    String age;//AGE
    String name;//BILL_NAME
    String proposer;//PROPOSER
    String approver;//APPROVER
    String pro_dt;//PROPOSE_DT
    String committee_id;//CURR_COMMITTEE_ID
    String curr_committee;//CURR_COMMITTEE
    String curr_committee_dt; //COMMITTEE_DT
    String url;//LINK_URL

    public PendingPetition toEntity(){
        return PendingPetition.builder()
                .bill_id(bill_id)
                .num(num)
                .age(age)
                .name(name)
                .proposer(proposer)
                .approver(approver)
                .pro_dt(pro_dt)
                .committee_id(committee_id)
                .curr_committee(curr_committee)
                .curr_committee_dt(curr_committee_dt)
                .url(url)
                .build();
    }


}
