package io.orange.mercadolivre.registerUser;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

    /**
     *
     * @param shouldBeASystemUser um objeto que deveria representar seu usuário logado
     * @return
     */
    UserDetails map(Object shouldBeASystemUser);
}