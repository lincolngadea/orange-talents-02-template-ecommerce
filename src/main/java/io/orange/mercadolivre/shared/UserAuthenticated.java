package io.orange.mercadolivre.shared;

import io.orange.mercadolivre.registerUser.UserAccount;
import io.orange.mercadolivre.registerUser.UserAccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserAuthenticated {

    private UserAccount usernameAuth;

    public UserAccount getUsernameAuth() {
        return usernameAuth;
    }
    public UserAccount verifyUserAuthenticated(UserAccountRepository userAccountRepository) throws ResponseStatusException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.usernameAuth = userAccountRepository.findByUsername(auth.getName()).get();
        return usernameAuth;
    }
}
