package io.orange.mercadolivre.registerAsks;

import io.orange.mercadolivre.registerAsks.request.NewAskRequest;
import io.orange.mercadolivre.registerAsks.response.AskResponseDetails;
import io.orange.mercadolivre.registerUser.UserAccount;
import io.orange.mercadolivre.registerUser.UserAccountRepository;
import io.orange.mercadolivre.shared.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre")
public class AskController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private UserAuthenticated userAuthenticated;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private Emails emails;

    @PostMapping("/question/{idProduct}")
    @Transactional
    public ResponseEntity<AskResponseDetails> createQuestion(@PathVariable("idProduct") Long id, @RequestBody @Valid NewAskRequest request){

        UserAccount usernameAuth = userAuthenticated.verifyUserAuthenticated(userAccountRepository);
        Ask newAsk = request.toModel(manager,usernameAuth,id);
        manager.persist(newAsk);
        emails.newAsk(newAsk);
        AskResponseDetails askResponseDetails = new AskResponseDetails(newAsk);
        return ResponseEntity.ok(askResponseDetails);
    }


}
