package net.codejava.token;

import net.codejava.model.User;

public interface ITokenVerification {
    boolean verifyUser(User user, String verificationCode);
    boolean verifyManager(User user, String verificationCode);
}
