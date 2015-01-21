package cz.uhk.restaurace.service.authentication;

import cz.uhk.restaurace.service.authentication.user.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by dann on 20.11.2014.
 * Custom authentication object created in order to store user details right after login
 */
public class CustomAuthentication extends UsernamePasswordAuthenticationToken implements Authentication {

    private static final long serialVersionUID = 22L;

    /**
     * Object created to store user data
     */
    private UserDetails user;

    public CustomAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,  UserDetails user) {
        super(principal, credentials, authorities);
        this.user = user;
    }

    public CustomAuthentication(Object principal, Object credentials,  UserDetails user) {
        super(principal, credentials);
        this.user = user;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser( UserDetails user) {
        this.user = user;
    }
}
