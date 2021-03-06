package io.orange.mercadolivre.registerProduct.request;

import io.orange.mercadolivre.registerProduct.Product;
import io.orange.mercadolivre.registerProduct.ProductOpinion;
import io.orange.mercadolivre.registerUser.UserAccount;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewProductOpinionRequest {


    @NotNull
    private @Min(1) @Max(5) Integer note;
    @NotBlank
    private String title;
    @NotBlank @Length(max = 500)
    private String description;

    @Deprecated
    public NewProductOpinionRequest() {
    }

    public NewProductOpinionRequest(
            @Length(min = 1, max = 5) @NotNull Integer note,
            @NotBlank String title,
            @NotBlank String description){
        this.note = note;
        this.title = title;
        this.description = description;
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

    public ProductOpinion toModel(EntityManager manager, Product product, UserAccount usernameAuth) {

        return new ProductOpinion(note,title,description,usernameAuth,product);
    }
}
