package io.orange.mercadolivre.registerImages;

import io.orange.mercadolivre.registerProduct.Product;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private Product product;
    @URL
    @NotBlank
    private String link;

    @Deprecated
    public ProductImage() {
    }

    public Product getProduct() {
        return product;
    }

    public String getLink() {
        return link;
    }

    public ProductImage(@NotNull @Valid Product product, @URL String link) {
        this.product = product;
        this.link = link;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductImage other = (ProductImage) obj;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }
}
