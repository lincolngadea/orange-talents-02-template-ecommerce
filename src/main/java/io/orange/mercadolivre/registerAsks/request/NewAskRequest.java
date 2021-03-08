package io.orange.mercadolivre.registerAsks.request;

import io.orange.mercadolivre.registerAsks.Ask;
import io.orange.mercadolivre.registerUser.UserAccount;

import javax.validation.constraints.NotBlank;

public class NewAskRequest {

    private @NotBlank String title;

    @Deprecated
    public NewAskRequest() {
    }

    public NewAskRequest(@NotBlank String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "NewQuestionRequest{" +
                "title='" + title + '\'' +
                '}';
    }

    public Ask toModel(UserAccount usernameAuth) {
        return new Ask(this.title, usernameAuth);
    }
}
