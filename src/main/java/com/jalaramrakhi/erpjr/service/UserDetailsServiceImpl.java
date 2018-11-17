package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private com.jalaramrakhi.erpjr.repository.UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] usernameAndCompany = StringUtils.split(
                username, String.valueOf(Character.LINE_SEPARATOR));
        if (usernameAndCompany == null || usernameAndCompany.length != 2) {
            throw new UsernameNotFoundException("Username and domain must be provided");
        }
        com.jalaramrakhi.erpjr.entity.User user = userRepository.findUserByCompany(usernameAndCompany[0], Integer.parseInt(usernameAndCompany[1]));
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("Username not found for domain, username=%s, domain=%s",
                            usernameAndCompany[0], usernameAndCompany[1]));
        }
        return new User(user.getUser_name(), user.getUser_password(), emptyList());
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        com.jalaramrakhi.erpjr.entity.User User = UserRepository.findUserByCompany(username,);
//        if (User == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new User(User.getUser_name(), User.getUser_password(), emptyList());
//    }
}