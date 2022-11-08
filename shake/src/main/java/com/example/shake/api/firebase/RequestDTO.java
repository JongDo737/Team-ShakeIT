package com.example.shake.api.firebase;

import lombok.Data;

@Data
public class RequestDTO {
    private String targetToken;
    private String title;
    private String body;
}
