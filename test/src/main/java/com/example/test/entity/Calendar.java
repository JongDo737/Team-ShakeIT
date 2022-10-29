package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 45)
    private String session;
}
