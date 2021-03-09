package io.orange.mercadolivre.payFinal;

import io.orange.mercadolivre.registerProduct.Product;
import io.orange.mercadolivre.registerUser.UserAccount;
import io.orange.mercadolivre.registerUser.UserAccountRepository;
import io.orange.mercadolivre.shared.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre")
public class PurchaseController {

    @PersistenceContext
    private EntityManager manager;//1

    @Autowired
    private UserAuthenticated userAuthenticated;//1

    @Autowired
    private UserAccountRepository userAccountRepository;//1

    //1
    @PostMapping("/compras")
    @Transactional
    public String postMethodName(
            @RequestBody @Valid NewPurchaseRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {

        Product productToPayment = manager.find(Product.class, request.getIdProduct());

        Integer quantity = request.getQuantity();
        boolean deduced = productToPayment.deductOfStock(quantity);

        //1
        if(deduced){
            UserAccount buyer = userAuthenticated.verifyUserAuthenticated(userAccountRepository);
            GatewayPurchase gateway = request.getGateway();

            Purchase newPurchase = new Purchase(productToPayment, quantity, buyer, gateway);//1

            manager.persist(newPurchase);

            String urlReturnPaymentMethod = uriComponentsBuilder
                        .path("/retorno-"+gateway+"/{id}")
                        .buildAndExpand(newPurchase.getId())
                        .toString();
            return gateway+".com/" + newPurchase.getId() + "?redirectUrl="+urlReturnPaymentMethod;
        }

        BindException stockProblem = new BindException(request,"newPurchaseRequest");
        stockProblem.reject(null,"Not be possible puchased because Stock");

        throw stockProblem;

    }

}
