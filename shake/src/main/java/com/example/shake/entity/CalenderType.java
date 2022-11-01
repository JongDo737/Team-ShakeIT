package com.example.shake.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "calendar_type")
public class CalenderType {
    @Id
    private int type;

    @Column(columnDefinition = "VARCHAR(45)", nullable = false)
    private String type_desc;
}
