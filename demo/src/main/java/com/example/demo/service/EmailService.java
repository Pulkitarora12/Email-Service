package com.example.demo.service;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    //send email to one person
    void sendEmail(String to, String subject, String message);

    //send email to multiple person
    void sendEmail(String[] to, String subject, String message);

    //sending email with html
    void sendEmailWithHTML(String to, String subject, String htmlContent);

    //sending email with files
    void sendEmailWithFile(String to, String subject, String message, File file);

    //sending email with files with user input
    void sendEmailWithFile(String to, String subject, String htmlContent, InputStream inputStream);
}
