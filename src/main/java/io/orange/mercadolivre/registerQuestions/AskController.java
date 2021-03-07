package io.orange.mercadolivre.registerQuestions;

import io.orange.mercadolivre.registerQuestions.request.NewAskControllerRequest;
import io.orange.mercadolivre.registerQuestions.response.AskResponseDetails;
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

    @PostMapping("/question/{idProduct}")
    @Transactional
    public ResponseEntity<AskResponseDetails> createQuestion(@PathVariable("idProduct") Long id, @RequestBody @Valid NewAskControllerRequest request){

        UserAccount usernameAuth = userAuthenticated.verifyUserAuthenticated(userAccountRepository);
        Ask ask = request.toModel(usernameAuth);
        manager.persist(ask);
        AskResponseDetails askResponseDetails = new AskResponseDetails(ask);
        return ResponseEntity.ok(askResponseDetails);
    }


}
