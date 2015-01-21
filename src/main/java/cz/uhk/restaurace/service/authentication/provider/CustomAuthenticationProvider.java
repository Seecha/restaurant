package cz.uhk.restaurace.service.authentication.provider;

import java.util.Collection;

import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.UserService;
import cz.uhk.restaurace.service.authentication.CustomAuthentication;
import cz.uhk.restaurace.service.authentication.user.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by dann on 20.11.2014.
 * Custom authentication provider which takes care of storing additional data in CustomAuthentication object right
 * after logging in
 */
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService userService;
    
    @Override
    public Authentication authenticate(Authentication authentication)  throws AuthenticationException{
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userService.getUserById(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if(!encoder.matches(password, user.getPassword())){
             throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getRoles();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, org.springframework.security.core.userdetails.UserDetails user) {
        CustomAuthentication result = null;
        UserDetails userProfile = (UserDetails) principal;
        if (principal != null) {
            result = new CustomAuthentication(userProfile.getUsername(), userProfile.getPassword(), userProfile.getRoles(), userProfile);
        }
        return result;
    }
}
