package io.orange.mercadolivre.registerAsks;

import io.orange.mercadolivre.registerProduct.Product;
import io.orange.mercadolivre.registerUser.UserAccount;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ask implements Comparable<Ask>{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String title;
    @ManyToOne @NotNull
    private UserAccount usernameAuth;
    @ManyToOne @NotNull
    private Product product;

    private LocalDateTime instanteDate = LocalDateTime.now();

    @Deprecated
    public Ask() {
    }

    public Ask(@NotBlank String title, UserAccount usernameAuth, Product product) {
        this.title = title;
        this.usernameAuth = usernameAuth;
        this.product = product;
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

    public Product getProduct() { return product;}

    @Override
    public String toString() {
        return "Ask{" +
                "title='" + title + '\'' +
                ", usernameAuth=" + usernameAuth +
                ", instanteDate=" + instanteDate +
                ", product =" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ask)) return false;
        Ask ask = (Ask) o;
        return title.equals(ask.title) && usernameAuth.equals(ask.usernameAuth) && product.equals(ask.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, usernameAuth, product);
    }

    @Override
    public int compareTo(@org.jetbrains.annotations.NotNull Ask o) {
        return this.title.compareTo(o.title);
    }
}
