package io.orange.mercadolivre.payFinal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewPurchaseRequest {

    @Positive
    private Integer quantity;
    @NotNull
    private Long idProduct;
    private GatewayPurchase gateway;

    @Deprecated
    public NewPurchaseRequest() {
    }

    public NewPurchaseRequest(
            @Positive Integer quantity,
            @NotNull Long idProduct,
            GatewayPurchase gateway) {
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.gateway = gateway;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public GatewayPurchase getGateway() {
        return gateway;
    }
}
