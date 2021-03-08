package io.orange.mercadolivre.registerAsks.response;

import io.orange.mercadolivre.registerAsks.Ask;

import java.time.LocalDateTime;

public class AskResponseDetails {

    private String title;
    private String usernameAuth;
    private LocalDateTime instanteDate;
    private String product;

    public AskResponseDetails(Ask ask){
        title = ask.getTitle();
        usernameAuth = ask.getUsernameAuth().getUsername();
        instanteDate = ask.getInstanteDate();
        product = ask.getProduct().getName();
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

    public String getProduct() {return product;}
}
