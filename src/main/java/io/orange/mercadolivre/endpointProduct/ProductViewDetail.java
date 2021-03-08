package io.orange.mercadolivre.endpointProduct;

import io.orange.mercadolivre.registerProduct.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ProductViewDetail {

    private final Set<String> linkImage;
    private String name;
    private String description;
    private BigDecimal price;
    private final Set<DetailProductCharacteristic> details;
    private final SortedSet<String> asks;
    private final Set<Map<String, String>> opinions;


    public ProductViewDetail(Product product) {
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        details = product.mapCharacetristics(  DetailProductCharacteristic::new);
        linkImage = product.mapImages(image -> image.getLink());
        asks = product.mapAsk(ask -> ask.getTitle());
        opinions = product.mapOpinion(opinion->{
            return Map.of(
                    "title", opinion.getTitle(),
                    "description",opinion.getDescription()
            );
        });
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<DetailProductCharacteristic> getDetails() {
        return details;
    }

    public Set<String> getLinkImage() {
        return linkImage;
    }

    public SortedSet<String> getAsks() {
        return asks;
    }

    public Set<Map<String, String>> getOpinions() {
        return opinions;
    }
}
