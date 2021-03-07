package io.orange.mercadolivre.registerQuestions.response;

import io.orange.mercadolivre.registerQuestions.Ask;

import java.time.LocalDateTime;

public class AskResponseDetails {

    private String title;
    private String usernameAuth;
    private LocalDateTime instanteDate;

    public AskResponseDetails(Ask ask){
        title = ask.getTitle();
        usernameAuth = ask.getUsernameAuth().getUsername();
        instanteDate = ask.getInstanteDate();
    }

    public String getTitle() {
        return title;
    }

    public String getUsernameAuth() {
        return usernameAuth;
    }

    public LocalDateTime getInstanteDate() {
        return instanteDate;
    }
}
