package io.orange.mercadolivre.registerAsks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    @Autowired
    private Mailer mailer;

    public void newAsk(@NotNull @Valid Ask ask) {
        mailer.send("<html>...</html>",ask.getUsernameAuth().getUsername(),"New ask:",ask.getInstanteDate(),ask.getTitle());
    }
}
