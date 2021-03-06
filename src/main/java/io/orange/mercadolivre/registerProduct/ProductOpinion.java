package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerUser.UserAccount;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class ProductOpinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private @Min(1) @Max(5)Integer note;
    @NotBlank
    private String title;
    @NotBlank @Length(max = 500)
    private String description;
    @NotNull
    @ManyToOne
    private UserAccount usernameAuth;
    @NotNull
    @ManyToOne
    private Product product;

    @Deprecated
    public ProductOpinion() {
    }

    public ProductOpinion(
            @Length(min = 1, max = 5) @NotNull Integer note,
            @NotBlank String title,
            @NotBlank String description,
            @NotNull UserAccount usernameAuth,
            @NotNull Product product) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.usernameAuth = usernameAuth;
        this.product = product;
    }

    public Integer getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserAccount getUsernameAuth() {
        return usernameAuth;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "ProductOpinion{" +
                "note=" + note +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", usernameAuth=" + usernameAuth +
                ", product=" + product +
                '}';
    }
}
