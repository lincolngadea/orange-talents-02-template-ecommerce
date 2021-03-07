package io.orange.mercadolivre.registerQuestions.request;

import io.orange.mercadolivre.registerQuestions.Ask;
import io.orange.mercadolivre.registerUser.UserAccount;

import javax.validation.constraints.NotBlank;

public class NewAskControllerRequest {

    private @NotBlank String title;

    @Deprecated
    public NewAskControllerRequest() {
    }

    public NewAskControllerRequest(@NotBlank String title) {
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
