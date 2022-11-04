package com.example.shake.dto;

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
    String num;
    String age;
    String name;
    String proposer;
    String approver;
    String pro_dt;
    String pro_result;
    String curr_committee;
    String url;
    String bill_id;
    String committee_id;
}
