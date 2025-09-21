package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.FieldPosition;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(message);
        msg.setFrom("pulkitarora0714@gmail.com");
        mailSender.send(msg);
        logger.info("Email has been sent");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(message);
        msg.setFrom("pulkitpulkitarr@gmail.com");
        mailSender.send(msg);
        logger.info("Email has been sent");
    }

    @Override
    public void sendEmailWithHTML(String[] to, String subject, String htmlContent) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setFrom("pulkitpulkitarr@gmail.com");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String[] to, String subject, String message, File file) {

        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom("pulkitpulkitarr@gmail.com");
            helper.setTo(to);
            helper.setText(message);
            FileSystemResource resource = new FileSystemResource(file);
            helper.addAttachment(resource.getFilename(), file);
            mailSender.send(msg);
            logger.info("Email has been sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String[] to, String subject, String message ,InputStream inputStream) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom("pulkitpulkitarr@gmail.com");
            helper.setTo(to);
            helper.setText(message);
            helper.setSubject(subject);

            File file = new File("src/main/resources/email/test.png");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            FileSystemResource resource = new FileSystemResource(file);
            helper.addAttachment(resource.getFilename(), file);
            mailSender.send(msg);
            logger.info("Email has been sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
