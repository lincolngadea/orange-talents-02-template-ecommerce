package io.orange.mercadolivre.registerQuestions;

import io.orange.mercadolivre.registerUser.UserAccount;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Ask {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String title;
    @ManyToOne @NotNull
    private UserAccount usernameAuth;
    private LocalDateTime instanteDate = LocalDateTime.now();

    @Deprecated
    public Ask() {
    }

    public Ask(@NotBlank String title, UserAccount usernameAuth) {
        this.title = title;
        this.usernameAuth = usernameAuth;
    }

    public String getTitle() {
        return title;
    }

    public UserAccount getUsernameAuth() {
        return usernameAuth;
    }

    public LocalDateTime getInstanteDate() {
        return instanteDate;
    }

    @Override
    public String toString() {
        return "Ask{" +
                "title='" + title + '\'' +
                ", usernameAuth=" + usernameAuth +
                ", instanteDate=" + instanteDate +
                '}';
    }
}
