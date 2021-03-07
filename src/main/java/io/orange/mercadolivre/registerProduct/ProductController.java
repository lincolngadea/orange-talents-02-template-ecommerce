package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerDetails.RejectsRepeatedDetails;
import io.orange.mercadolivre.registerImages.NewImagesRequest;
import io.orange.mercadolivre.registerImages.UploadFiles;
import io.orange.mercadolivre.registerProduct.request.NewProductOpinionRequest;
import io.orange.mercadolivre.registerProduct.request.NewProductRequest;
import io.orange.mercadolivre.registerProduct.response.ProductOpinionResponse;
import io.orange.mercadolivre.registerProduct.response.ProductResponse;
import io.orange.mercadolivre.registerUser.UserAccount;
import io.orange.mercadolivre.registerUser.UserAccountRepository;
import io.orange.mercadolivre.shared.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/mercadolivre")
public class ProductController {

    @Autowired
    private UploadFiles uploadFiles;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserAuthenticated userAuthenticated;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @InitBinder(value = "newProductRequest")
    public void init(WebDataBinder webDataBinder){

        webDataBinder.addValidators(new RejectsRepeatedDetails());
    }

    @PostMapping("/produto")
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody @Valid NewProductRequest request){

        UserAccount usernameAuth = userAuthenticated.verifyUserAuthenticated(userAccountRepository);

        Product product = request.toModel(manager, usernameAuth);
        manager.persist(product);
        ProductResponse productResponse = new ProductResponse(product);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/produto/{id}/images")
    @Transactional
    public String addImages(@PathVariable("id") Long id, @Valid NewImagesRequest request) {

        UserAccount usernameAuth = userAuthenticated.verifyUserAuthenticated(userAccountRepository);
        Product product = manager.find(Product.class, id);
        productValidId(usernameAuth, product);

        Set<String> links = uploadFiles.sendFile(request.getImages());
        product.imagesAssociations(links);

        manager.merge(product);

        return product.toString();
    }

    @PostMapping("produto/{id}/opiniao")
    @Transactional
    public ResponseEntity<?> productOpinio(@PathVariable("id") Long id, @RequestBody @Valid NewProductOpinionRequest request){

        Product product = manager.find(Product.class,id);
        UserAccount usernameAuth = userAuthenticated.verifyUserAuthenticated(userAccountRepository);
        productValidId(usernameAuth, product);

        ProductOpinion productOpinion = request.toModel(manager,product,usernameAuth);

        manager.persist(productOpinion);
        return ResponseEntity.ok(new ProductOpinionResponse(productOpinion));
    }
    /**
     *
     * @param usernameAuth validate
     * @param product id validate
     */
    private void productValidId(UserAccount usernameAuth, Product product) {
        if(!product.belongsToTheUser(usernameAuth)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}

