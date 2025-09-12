package com.example.demo;

import com.example.demo.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

//    public EmailSenderTest(EmailService emailService) {  //never use constructor in testing as junit doesnt know how too configure
//        this.emailService = emailService;
//    }

    @Test
    void emailSendTest() {
        System.out.println("Sending email");
        emailService.sendEmail("pulkitarora0707@gmail.com", "This is a test mail", "Testing the new Email service for project");
    }

    @Test
    void multipleemailSendTest() {
        System.out.println("Sending email");

        String[] to = new String[3];
        to[0] = "pulkitarora0714@gmail.com";
        to[1] = "pulkitarora0707@gmail.com";
        to[2] = "pulkitdrishti7@gmail.com";
        emailService.sendEmail(to, "This is a test mail", "Testing the new Email service for project");
    }

    @Test
    void HTMLemailSendTest() {
        System.out.println("Sending email");
        String html = "" +
                "<h1 style='color:red;border:1px solid red'>This is just a test mail</h1>"
                + "";
        emailService.sendEmailWithHTML(new String[]{"pulkitarora0707@gmail.com"}, "testing html mail", html);
    }

    @Test
    void FileemailSendTest() {
        System.out.println("Sending email");
        emailService.sendEmailWithFile(new String[]{"pulkitarora0707@gmail.com"},
                "Sending email with file",
                "Just testing a file sending service",
                new File("C:\\Users\\PULKIT ARORA\\Desktop\\Project\\emai_service\\demo (1)\\demo\\src\\main\\resources\\static\\images\\WhatsApp Image 2025-07-17 at 12.12.35_fe54867f.jpg"));
    }

    @Test
    void FileWithStreamemailSendTest() {

        File file = new File("C:\\Users\\PULKIT ARORA\\Desktop\\Project\\emai_service\\demo (1)\\demo\\src\\main\\resources\\static\\images\\WhatsApp Image 2025-07-17 at 12.12.35_fe54867f.jpg");

        try {
            InputStream is = new FileInputStream(file);

            System.out.println("Sending email");
            emailService.sendEmailWithFile(new String[]{"pulkitarora0707@gmail.com"},
                    "Sending email with file",
                    "Just testing a file sending service",
                    is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
