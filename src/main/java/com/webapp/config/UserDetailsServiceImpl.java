package com.webapp.config;

import com.webapp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by sednor-5 on 4/5/17.
 */
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.webapp.entity.User user = userDao.findByName(username);
        if(user != null) {
            return new User(user.getUserName(), user.getPassword(), true, true, true, user.isStatus(),
                    AuthorityUtils.createAuthorityList("USER"));
        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }
}
