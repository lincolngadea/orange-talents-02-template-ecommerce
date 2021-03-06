package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerDetails.RejectsRepeatedDetails;
import io.orange.mercadolivre.registerImages.NewImagesRequest;
import io.orange.mercadolivre.registerUser.UserAccount;
import io.orange.mercadolivre.registerUser.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserAccountRepository userAccountRepository;

    @InitBinder(value = "newProductRequest")
    public void init(WebDataBinder webDataBinder){

        webDataBinder.addValidators(new RejectsRepeatedDetails());
    }

    @PostMapping("/product")
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody @Valid NewProductRequest request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAccount usernameAuth = userAccountRepository.findByUsername(auth.getName()).get();
        Product product = request.toModel(manager, usernameAuth);
        manager.persist(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product/{id}/images")
    @Transactional
    public String addImages(@PathVariable("id") Long id, @Valid NewImagesRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAccount usernameAuth = userAccountRepository.findByUsername(auth.getName()).get();

        Product product = manager.find(Product.class, id);

        if(!product.pertenceAoUsuario(usernameAuth)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploadFiles.sendFile(request.getImages());
        product.imagesAssociations(links);

        manager.merge(product);

        return product.toString();
    }
}

