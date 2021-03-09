package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerAsks.Ask;
import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.registerDetails.NewDetailsRequest;
import io.orange.mercadolivre.registerImages.ProductImage;
import io.orange.mercadolivre.registerUser.UserAccount;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String name;
    private @NotNull @Positive BigDecimal price;
    private @NotNull @Min(0) Integer availableQuantity;
    private @NotBlank @Length(max = 1000) String description;
    private @ManyToOne @NotNull @Valid Category category;
    private @NotNull @Valid @ManyToOne UserAccount usernameAuth;

    private LocalDateTime instantDate = LocalDateTime.now();

    @OneToMany(mappedBy ="product", cascade = CascadeType.PERSIST)
    private Set<DetailProduct> details = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductImage> images = new HashSet<>();
    @OneToMany(mappedBy = "product")
    @OrderBy("title asc")
    private SortedSet<Ask> asks = new TreeSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductOpinion> opinions = new HashSet<>();

    @Deprecated
    public Product() {
    }

    public Product(
            @NotBlank String name,
            @NotNull @DecimalMin(value = "1.0", inclusive = false)  @Positive BigDecimal price,
            @NotNull @Min(0) Integer availableQuantity,
            @NotNull String description,
            @NotNull @Valid Category category,
            @NotBlank UserAccount usernameAuth,
            Collection<NewDetailsRequest> details) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.category = category;
        this.usernameAuth = usernameAuth;
        this.details.addAll(details
                .stream().map(detail -> detail.toModel(this))
                .collect(Collectors.toSet()));

        Assert.isTrue(this.details.size()>=3,"All product must have minimous of 3 characters!");
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<DetailProduct> getDetails() {return details;}

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public UserAccount getUsernameAuth() { return usernameAuth; }

    public Set<ProductImage> getImages() {return images; }

    public Category getCategory() {return category; }

    public LocalDateTime getInstantDate() {
        return instantDate;
    }

    public <T> Set<T> mapCharacetristics(Function<DetailProduct, T> mapFunction){
                return this.details.stream().map(mapFunction)
                        .collect(Collectors.toSet());
    }

    public <T> Set<T> mapImages(Function<ProductImage, T> mapFunction){
        return this.images.stream().map(mapFunction)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapAsk(Function<Ask, T> mapFunction){
        return this.asks.stream().map(mapFunction)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public <T> Set<T> mapOpinion(Function<ProductOpinion, T> mapFunction){
        return this.opinions.stream().map(mapFunction)
                .collect(Collectors.toSet());
    }

    public boolean deductOfStock(@Positive Integer quantity) {
        Assert.isTrue(quantity > 0, "The amount must be greater than zero. "+quantity);
        if(quantity <= this.availableQuantity){
            this.availableQuantity -= quantity;
            return true;
        }
        return false;
    }

    public void imagesAssociations(Set<String> links) {
        Set<ProductImage> images = links.stream().map(link -> new ProductImage(this, link))
                .collect(Collectors.toSet());
        this.images.addAll(images);
    }

    public boolean belongsToTheUser(UserAccount possibleOwner) {
        return this.usernameAuth.equals(possibleOwner);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", instantDate=" + instantDate +
                ", images=" + images +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
