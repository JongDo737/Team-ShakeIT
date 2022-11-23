package com.example.shake.dto;

import com.example.shake.entity.Bill;
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
    String daesu;//AGE
    String bill_num;//BILL_NO
    String bill_name;//BILL_NM
    String propersor;//PROPOSER
    String committee_nm;//COMMITTEE_NM
    String proc_result;//PROC_RESULT_CD
    String proc_date;//PROPOSE_DT
    String curr_trans_dt;//CURR_TRANS_DT
    String announce_dt;//ANNOUNCE_DT
    String url;//LINK_URL
    String rgs_proc_dt;//RGS_PROC_DT
    String bill_id;//BILL_ID
    String curr_committee_id;//CURR_COMMITTEE_ID
    private String create_date;        // 생성날짜
    private String update_date;        // 수정날짜
    public Bill toEntity() {
        return Bill.builder()
                .daesu(daesu)
                .bill_num(bill_num)
                .bill_name(bill_name)
                .propersor(propersor)
                .committee_nm(committee_nm)
                .proc_result(proc_result)
                .proc_date(proc_date)
                .curr_trans_dt(curr_trans_dt)
                .announce_dt(announce_dt)
                .url(url)
                .rgs_proc_dt(rgs_proc_dt)
                .billid(bill_id)
                .curr_committee_id(curr_committee_id)
                .create_date(create_date)
                .update_date(update_date)
                .build();
    }
}
