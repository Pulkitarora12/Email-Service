package com.example.demo.controller.api;

import com.example.demo.Helper.CustomResponse;
import com.example.demo.Helper.EmailRequest;
import com.example.demo.service.EmailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        emailService.
                sendEmailWithHTML(request.getTo(), request.getSubject(), request.getMessage());

        return ResponseEntity.ok(CustomResponse.builder().message("Email Sent Successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build()
        );
    }

    @PostMapping("/sendfile")
    public ResponseEntity<?> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws IOException {

        emailService.
                sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());

        return ResponseEntity.ok(CustomResponse.builder().message("Email Sent Successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build()
        );
    }
}
