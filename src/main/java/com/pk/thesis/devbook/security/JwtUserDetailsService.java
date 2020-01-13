package com.pk.thesis.devbook.security;

import java.util.ArrayList;

import com.pk.thesis.devbook.enpoint.user.UserDAO;
import com.pk.thesis.devbook.enpoint.user.UserDTO;
import com.pk.thesis.devbook.enpoint.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = userRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserDAO save(UserDTO user) {
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUsername(user.getUsername());
        newUserDAO.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUserDAO);
    }
}