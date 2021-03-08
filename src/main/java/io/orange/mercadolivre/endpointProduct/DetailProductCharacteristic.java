package io.orange.mercadolivre.endpointProduct;

import io.orange.mercadolivre.registerProduct.DetailProduct;

public class DetailProductCharacteristic {

    private String name;
    private String description;

    public DetailProductCharacteristic(DetailProduct characteristic) {
        name = characteristic.getName();
        description = characteristic.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
