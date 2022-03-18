package net.codejava.email;

import lombok.extern.slf4j.Slf4j;
import net.codejava.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Slf4j @Component
public class EmailSender implements IEmailSender{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendEmail(User user, String siteURL) {
        String toAddress = user.getEmail();
        String fromAddress = "abdelkarim.elbouroumi@usmba.ac.ma";
        String senderName = "Karim Official";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getFullName());
            String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);
            mailSender.send(message);
            log.info("email sent Success");
        } catch (MessagingException | MailException | UnsupportedEncodingException e) {
            return false;
        }

        return true;

    }
}
