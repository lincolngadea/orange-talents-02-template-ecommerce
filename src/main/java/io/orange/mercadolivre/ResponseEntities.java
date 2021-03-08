package io.orange.mercadolivre;

import io.orange.mercadolivre.registerAsks.Ask;
import io.orange.mercadolivre.registerImages.ProductImage;
import io.orange.mercadolivre.registerProduct.DetailProduct;
import io.orange.mercadolivre.registerProduct.Product;
import io.orange.mercadolivre.registerProduct.ProductOpinion;

import java.math.BigDecimal;
import java.util.Set;

public class ResponseEntities {

    private Set<ProductImage> link;
    private String productName;
    private BigDecimal price;
    private Set<DetailProduct> details;
    private String description;
    private String opinion;
    private String ask;

    public void ResponseEntity(Product product,ProductOpinion productOpinion, Ask ask){
        link = product.getImages();
        productName = product.getName();
        price = product.getPrice();
        details = product.getDetails();
        description = product.getDescription();
        opinion = productOpinion.getDescription();
        this.ask = ask.getTitle();
    }

    public Set<ProductImage> getLink() {
        return link;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<DetailProduct> getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public String getOpinion() {
        return opinion;
    }

    public String getAsk() {
        return ask;
    }
}
