package net.codejava.service;

import net.codejava.model.Role;
import net.codejava.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    List<User> listAll();

    void register(User user, String siteURL)
            throws UnsupportedEncodingException, MessagingException;

    void registerUser(User user, String siteURL);

    void saveRole(Role role_user);

    void addRoleToUser(String email, String roleName);
    boolean verify(String verificationCode);
}
