package io.orange.mercadolivre.endpointProduct;

import io.orange.mercadolivre.registerProduct.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/mercadolivre")
public class EndPointProductController {

    @Autowired
    EntityManager manager;

    @GetMapping("produto/{id}")
    @Transactional
    public ProductViewDetail getMethodName(@PathVariable("id") Long id){
        Product productSelected = manager.find(Product.class,id);
        ProductViewDetail productViewDetail = new ProductViewDetail(productSelected);
        return productViewDetail;
    }



}
