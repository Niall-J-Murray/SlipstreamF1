package me.niallmurray.slipstream.web;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.service.EmailService;
import me.niallmurray.slipstream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
  @Autowired
  EmailService emailService;
  @Autowired
  UserService userService;

  @PostMapping("/sendEmail")
  public String postSendEmail(@Valid @ModelAttribute("emailType") int emailType,
                              @Valid @ModelAttribute("emailAddress") String emailAddress) {

    User user = userService.findByEmail(emailAddress);

    if (emailType == 1) {
      emailService.sendEmail(
              emailAddress,
              "Slipstream Draft Notification",
              "Hi " + user.getUsername() + ", it's your turn to pick!\n" +
                      "Click the link below to login and make your draft pick.\n" +
                      "\n" +
                      "Link:\n" +
                      "https://slipstreamf1-production.up.railway.app/login");
    }

    if (emailType == 2) {
      emailService.sendEmailFromTemplate(
              emailAddress,
              "Slipstream Draft Notification",
              user.getUsername());
    }

    if (emailType == 3) {
      try {
        emailService.sendHtmlEmail(
                user.getUsername(),
                emailAddress,
                "Slipstream Draft Notification");
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      }
    }

    if (emailType == 4) {
      try {
        emailService.sendEmailFromHTMLTemplate(
                user.getUsername(),
                emailAddress,
                "Slipstream Draft Notification");
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      }
    }
    return "redirect:/admin";
  }
}
