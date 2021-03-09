package io.orange.mercadolivre.payFinal;

import io.orange.mercadolivre.registerProduct.Product;
import io.orange.mercadolivre.registerUser.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Product chosenProduct;
    @Positive
    private Integer quantity;
    @ManyToOne
    @NotNull
    @Valid
    private UserAccount buyer;
    @Enumerated
    @NotNull
    private GatewayPurchase gateway;

    public Purchase(
            @NotNull @Valid Product chosenProduct,
            @Positive Integer quantity,
            @NotNull @Valid UserAccount buyer, @NotNull GatewayPurchase gateway) {
        this.chosenProduct = chosenProduct;
        this.quantity = quantity;
        this.buyer = buyer;
        this.gateway = gateway;
    }

    public Product getChosenProduct() {
        return chosenProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public UserAccount getBuyer() {
        return buyer;
    }

    public GatewayPurchase getGateway() { return gateway; }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "chosenProduct=" + chosenProduct +
                ", quantity=" + quantity +
                ", buyer=" + buyer +
                ", gatewayPurchase=" + gateway +
                '}';
    }


}
