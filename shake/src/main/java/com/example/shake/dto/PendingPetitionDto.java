package com.example.shake.dto;

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



}
