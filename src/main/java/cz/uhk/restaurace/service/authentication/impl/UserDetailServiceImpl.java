package cz.uhk.restaurace.service.authentication.impl;

import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.UserService;
import cz.uhk.restaurace.service.authentication.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by dann on 20.11.2014.
 * Object, which stores user data in order to use them in views.
 * It is being managed by spring security in CustomAuthentication object.
 */

@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;


    /**
     * Entity user is loaded by username on authentication, some of its fields are than mapped on this UserDetailService object.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = null;

        if(!"".equals(username) && username != null){
            User tempUser = userService.getUserById(username);
            if (tempUser  != null) {
                user = new UserDetails(tempUser.getUsername(),
                        tempUser.getPassword(),
                        tempUser.getRoles(),
                        tempUser.getSurname(),
                        tempUser.getFirstname(),
                        tempUser.getEmail(),
                        tempUser.getTelephone(),
                        tempUser.getAddress(),
                        tempUser.getRoles());
            }
        }

        return user;
    }
}
