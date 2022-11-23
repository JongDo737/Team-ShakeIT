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

    private String create_date;        // 생성날짜
    private String update_date;        // 수정날짜

    public PendingPetition toEntity(){
        return PendingPetition.builder()
                .billid(bill_id)
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
                .create_date(create_date)
                .update_date(update_date)
                .build();
    }


}
