package spring.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import spring.app.services.PersonDetailsServiceImpl;

import java.util.Collections;


/**
 * Here we will compare the username/password from the form and from the database
 */

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsServiceImpl personDetailsServiceImpl;

    @Autowired
    public AuthProviderImpl(PersonDetailsServiceImpl personDetailsServiceImpl) {
        this.personDetailsServiceImpl = personDetailsServiceImpl;
    }

    @Override  // authentication - login/password
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails personDetails =  personDetailsServiceImpl.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();
        if (!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Wrong password");

        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
