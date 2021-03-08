package io.orange.mercadolivre.registerAsks.request;

import io.orange.mercadolivre.registerAsks.Ask;
import io.orange.mercadolivre.registerProduct.Product;
import io.orange.mercadolivre.registerUser.UserAccount;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NewAskRequest {

    private @NotBlank String title;
    private Long idProduct;

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

    public Ask toModel(EntityManager manager,UserAccount usernameAuth,Long id) {
        this.idProduct = id;
        Assert.state(this.idProduct!=null,"O Id de produto não pode ser nulo");
        Product product = manager.find(Product.class, id);
        return new Ask(this.title,usernameAuth,product);
    }
}
