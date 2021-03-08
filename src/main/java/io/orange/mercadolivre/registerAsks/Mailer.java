package io.orange.mercadolivre.registerAsks;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public interface Mailer {

    /**
     *
     * @param mailBody
     * @param fromUser
     * @param subject
     * @param dateAsk
     * @param ask
     */
    void send(
            @NotBlank String mailBody,
            @NotBlank @Email String fromUser,
            @NotBlank String subject,
            @NotBlank LocalDateTime dateAsk,
            @NotBlank String ask);
}
