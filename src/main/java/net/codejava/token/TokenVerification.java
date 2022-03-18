package net.codejava.token;

import net.codejava.model.User;
import net.codejava.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenVerification implements ITokenVerification{

    @Override
    public boolean verifyUser(User user, String verificationCode) {
        return user != null && !user.isEnabled();

    }

    @Override
    public boolean verifyManager(User user, String verificationCode) {
        return false;
    }
}
