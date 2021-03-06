package io.orange.mercadolivre.registerProduct.response;

import io.orange.mercadolivre.registerProduct.ProductOpinion;

public class ProductOpinionResponse {

    private Integer note;
    private String title;
    private String description;
    private String usernameAuth;
    private String product;

    public ProductOpinionResponse(ProductOpinion productOpinion){
        note = productOpinion.getNote();
        title = productOpinion.getTitle();
        description = productOpinion.getDescription();
        usernameAuth = productOpinion.getUsernameAuth().getUsername();
        product = productOpinion.getProduct().getName();
    }

    public Integer getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getUsernameAuth() {
        return usernameAuth;
    }

    public String getDescription() {
        return description;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "ProductOpinionResponse{" +
                "note=" + note +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", usernameAuth='" + usernameAuth + '\'' +
                ", product=" + product +
                '}';
    }
}
