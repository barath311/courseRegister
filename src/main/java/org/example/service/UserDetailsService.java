package org.example.service;

import org.example.model.AppUser;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().replace("ROLE_", "")) // ROLE_USER → USER
                .build();
    }
}
