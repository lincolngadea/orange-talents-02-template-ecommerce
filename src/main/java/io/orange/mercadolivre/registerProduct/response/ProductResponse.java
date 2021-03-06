package io.orange.mercadolivre.registerProduct.response;

import io.orange.mercadolivre.registerProduct.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductResponse {

    private String name;
    private BigDecimal price;
    private Integer availableQuantity;
    private String description;
    private String category;
    private String usernameAuth;
    private LocalDateTime localDateTime;
    private Set<String> details;

    public ProductResponse(Product product){
        name = product.getName();
        price = product.getPrice();
        availableQuantity = product.getAvailableQuantity();
        description = product.getDescription();
        category = product.getCategory().getName();
        usernameAuth = product.getUsernameAuth().getUsername();
        localDateTime = product.getInstantDate();
        details = product.getDetails().stream().map(detail -> detail.toString())
                .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getUsernameAuth() {
        return usernameAuth;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Set<String> getDetails() {
        return details;
    }
}
