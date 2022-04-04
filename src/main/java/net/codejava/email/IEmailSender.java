package net.codejava.email;

import net.codejava.model.User;

public interface IEmailSender {
    boolean sendEmail(User user, String siteURL);
    boolean getEmail(String name, String email, String message);
}
