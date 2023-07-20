package me.niallmurray.slipstream.web;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import me.niallmurray.slipstream.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
  @Autowired
  EmailService emailService;

  @PostMapping("/sendEmail")
  public String postSendEmail(@Valid @ModelAttribute("emailType") int emailType,
                              @Valid @ModelAttribute("emailAddress") String emailAddress) {
    if (emailType == 1) {
      emailService.sendEmail(
              emailAddress,
              "Test Email Type 1",
              "This is a test email for a basic custom email.");
    }

    if (emailType == 2) {
      emailService.sendEmailFromTemplate(
              emailAddress,
              "Test Email Type 2");
    }

    if (emailType == 3) {
      try {
        emailService.sendHtmlEmail(
                emailAddress,
                "Test Email Type 3");
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      }
    }

    if (emailType == 4) {
      try {
        emailService.sendEmailFromHTMLTemplate(
                emailAddress,
                emailAddress,
                "Test Email Type 4");
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      }
    }
    return "redirect:/admin";
  }
}
