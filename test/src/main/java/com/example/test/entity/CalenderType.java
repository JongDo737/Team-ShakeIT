package com.example.test.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "calendar_type")
public class CalenderType {
    @Id
    private int type;
    @Column(length = 45)
    private String type_desc;
}
