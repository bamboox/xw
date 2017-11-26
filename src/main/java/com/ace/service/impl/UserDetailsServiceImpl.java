package com.ace.service.impl;

import com.ace.entity.User;
import com.ace.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author zhaoxinguo on 2017/9/13.
 */
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository myUserRepository;

    // 通过构造器注入MyUserRepository
    public UserDetailsServiceImpl(UserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = myUserRepository.getByUsername(username);
        if(myUser == null){
            throw new UsernameNotFoundException(username);
        }
//        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), emptyList());
        return myUser;
    }

}
