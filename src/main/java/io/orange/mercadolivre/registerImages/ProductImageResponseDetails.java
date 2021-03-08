package io.orange.mercadolivre.registerImages;

import io.orange.mercadolivre.registerProduct.Product;

public class ProductImageResponseDetails {

    private Product product;
    private String link;

    public ProductImageResponseDetails(ProductImage productImage){
        product = productImage.getProduct();
        link = productImage.getLink();
    }

    public Product getProduct() {
        return product;
    }

    public String getLink() {
        return link;
    }
}
