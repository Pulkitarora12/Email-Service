package com.example.demo.Helper;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse {

    private String message;
    private HttpStatus status;
    private boolean success = false;
}
