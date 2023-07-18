package me.niallmurray.slipstream.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service("emailService")
public class EmailService {
  @Autowired
  ResourceLoader resourceLoader;

  @Autowired
  JavaMailSender mailSender;


  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);

    mailSender.send(message);
  }

  public void sendEmailFromTemplate(String to, String subject) {
    Resource resource = new ClassPathResource("static/email/emailText.txt");
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    InputStream inputStream = null;
    String emailBody;
    try {
      inputStream = resource.getInputStream();
      byte[] buffer = new byte[1024];
      for (int length; (length = inputStream.read(buffer)) != -1; ) {
        outputStream.write(buffer, 0, length);
      }
      emailBody = outputStream.toString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(emailBody);

    mailSender.send(message);
  }

  public void sendHtmlEmail(String emailRecipient, String subject) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    message.setFrom(new InternetAddress("slipstream.f1.draft@gmail.com"));
    message.setRecipients(MimeMessage.RecipientType.TO, emailRecipient);
    message.setSubject(subject);

    String htmlContent = "<h1>This is a test Spring Boot HTML custom email</h1>\n" + "<p>It can contain <strong>HTML</strong> content.</p>\n" + "\n" + "<a href=\"https://slipstreamf1-production.up.railway.app/login\">Go to Slipstream site</a>";
    message.setContent(htmlContent, "text/html; charset=utf-8");

    mailSender.send(message);
  }

  public void sendEmailFromHTMLTemplate(String emailRecipient, String subject) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    message.setFrom(new InternetAddress("slipstream.f1.draft@gmail.com"));
    message.setRecipients(MimeMessage.RecipientType.TO, emailRecipient);
    message.setSubject(subject);

    // Read the HTML template into a String variable
    FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/email/emailTemplate.html"));
    String htmlTemplate = null;
    try {
      htmlTemplate = file.getContentAsString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Replace placeholders in the HTML template with dynamic values
    htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
    htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

    // Set the email's content to be the HTML template
    message.setContent(htmlTemplate, "text/html; charset=utf-8");

    mailSender.send(message);
  }
}