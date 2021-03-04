package io.orange.mercadolivre.registerProduct;

import io.jsonwebtoken.lang.Assert;
import io.orange.mercadolivre.config.validators.annotations.ExistsId;
import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.registerDetails.NewDetailsRequest;
import io.orange.mercadolivre.registerUser.UserAccount;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewProductRequest {

    @NotBlank
    private String name;
    @NotNull
    @DecimalMin(value = "1.0", inclusive = false)
    @Positive
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Integer availableQuantity;
    @NotBlank
    @Column(length = 1000)
    private String description;
    @NotNull
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long idCategory;
    @Size(min = 3)
    @Valid
    private List<NewDetailsRequest> details = new ArrayList<>();


    @Deprecated
    public NewProductRequest() {
    }

    public NewProductRequest(
            @NotBlank String name,
            @NotNull @DecimalMin(value = "1.0", inclusive = false)
            @Positive BigDecimal price,
            @NotNull @Min(0) Integer availableQuantity,
            @NotBlank String description,
            @NotNull Long idCategory, @Size(min = 3) @Valid List<NewDetailsRequest> details) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.idCategory = idCategory;
        this.details.addAll(details);

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

    public Long getIdCategory() {
        return idCategory;
    }

    public List<NewDetailsRequest> getDetails() {
        return details;
    }

    public Product toModel(EntityManager manager, UserAccount usernameAuth) {
        @NotNull Category category = manager.find(Category.class,this.idCategory);
        Assert.state(category!=null,"The category dont exists. id:"+this.idCategory);
        return new Product(this.name,this.price,this.availableQuantity,this.description,category, usernameAuth);
    }

    @Override
    public String toString() {
        return "NewProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", description='" + description + '\'' +
                ", idCategory=" + idCategory +
                ", details=" + details +
                '}';
    }

    public Set<String> searchEqualDetails() {
        HashSet<String> namesEquals = new HashSet<>();
        HashSet<String> results = new HashSet<>();
        for(NewDetailsRequest detail : details){
            String name = detail.getTitle();
            if(!namesEquals.add(name)){
                results.add(name);
            }
        }
        return results;
    }
}
