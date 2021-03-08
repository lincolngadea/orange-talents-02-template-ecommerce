package io.orange.mercadolivre.registerUser.response;

import io.orange.mercadolivre.registerUser.UserAccount;

import java.time.LocalDateTime;

public class UserAccountResponseDetails {

    private String username;
    private LocalDateTime localDateTime;

    public UserAccountResponseDetails(UserAccount userAccount){
        username = userAccount.getUsername();
        localDateTime = userAccount.getLocalDateTime();
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
