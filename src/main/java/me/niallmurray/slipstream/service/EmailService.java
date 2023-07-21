package me.niallmurray.slipstream.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service("emailService")
public class EmailService {
  @Autowired
  ResourceLoader resourceLoader;
  @Autowired
  JavaMailSender mailSender;
  @Autowired
  TeamService teamService;
  @Autowired
  LeagueService leagueService;
  @Autowired
  private UserRepository userRepository;

  public void sendPickNotification(League league) {
    User user = teamService.getNextToPick(league);
    if (user != null) {
      if (Boolean.FALSE.equals(user.getIsTestUser())) {
        if (user.getTeam().getFirstPickNumber() != 11) {
          if (user.getEmailsReceived() < 3) {
            try {
              sendEmailFromHTMLTemplate(user.getUsername(),
                      user.getEmail(),
                      "Slipstream Draft Notification");
              user.setEmailsReceived(user.getEmailsReceived() + 1);
              userRepository.save(user);
              sendPickNotificationCopy(user.getUsername(), user.getEmail(), league.getLeagueName());

            } catch (MessagingException e) {
              throw new RuntimeException(e);
            }
          }
        }
      }
    }
  }

  public void sendPickNotificationCopy(String username, String email, String league) {
    sendEmail("slipstream.f1.draft@gmail.com",
            "Pick notice sent to " + username,
            "Automated draft pick notification sent to "
                    + username + " at " + email + " for " + league);
  }

  @Async
  public CompletableFuture asyncPickNotificationEmail(League userLeague) {
    sendPickNotification(userLeague);
    return CompletableFuture.completedFuture("Pick email sent.");
  }

  public void sendActiveLeagueNotice(League league) {
    List<Team> teams = league.getTeams();
    for (Team team : teams) {
      User user = team.getUser();
      if (user != null) {
        if (Boolean.FALSE.equals(user.getIsTestUser())) {
          if (user.getEmailsReceived() < 4) {
            sendEmail(user.getEmail(),
                    "Slipstream League Active",
                    """
                            Hi %s,
                            Your Slipstream F1 league is now active.
                            Check back after the next race to see your score!
                            https://slipstreamf1-production.up.railway.app/login
                            """.formatted(user.getUsername()));
            user.setEmailsReceived(user.getEmailsReceived() + 1);
            userRepository.save(user);
            sendActiveNotificationCopy(league.getLeagueName());
          }
        }
      }
    }
  }

  public void sendActiveNotificationCopy(String league) {
    sendEmail("slipstream.f1.draft@gmail.com",
            "Active notice sent to " + league,
            "Automated active league notification sent for " + league);
  }

  @Async
  public CompletableFuture asyncActiveNotificationEmail(League userLeague) {
    sendActiveLeagueNotice(userLeague);
    return CompletableFuture.completedFuture("Active email sent.");
  }

  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);

    mailSender.send(message);
  }

  public void sendEmailFromTemplate(String to, String subject, String username) {
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

    emailBody = emailBody.replace("${name}", username);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(emailBody);

    mailSender.send(message);
  }

  public void sendHtmlEmail(String username, String emailRecipient, String subject) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    message.setFrom(new InternetAddress("slipstream.f1.draft@gmail.com"));
    message.setRecipients(MimeMessage.RecipientType.TO, emailRecipient);
    message.setSubject(subject);

    String htmlContent = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <title>Draft Pick Notice</title>
            </head>
            <body>
            <div>
              <h1>Hi ${name}, it's your turn to pick!</h1>
              <h3>Click the link below to login and make your draft pick.</h3>
              <a href="https://slipstreamf1-production.up.railway.app/login">Go to Slipstream site.</a>
            </div>
            </body>
            </html>
            """;
    htmlContent = htmlContent.replace("${name}", username);
    message.setContent(htmlContent, "text/html; charset=utf-8");

    mailSender.send(message);
  }

  public void sendEmailFromHTMLTemplate(String username, String emailRecipient, String subject) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    message.setFrom(new InternetAddress("slipstream.f1.draft@gmail.com"));
    message.setRecipients(MimeMessage.RecipientType.TO, emailRecipient);
    message.setSubject(subject);

    // Read the HTML template into a String variable
    FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/email/emailTemplate.html"));
    String htmlTemplate;
    try {
      htmlTemplate = file.getContentAsString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Replace placeholders in the HTML template with dynamic values
    htmlTemplate = htmlTemplate.replace("${name}", username);
    htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

    // Set the email's content to be the HTML template
    message.setContent(htmlTemplate, "text/html; charset=utf-8");

    mailSender.send(message);
  }
}