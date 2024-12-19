package com.creaturelove.ecommercebackend.service;

import com.creaturelove.ecommercebackend.db.entity.CartEntity;
import com.creaturelove.ecommercebackend.db.entity.UserEntity;
import com.creaturelove.ecommercebackend.db.repository.CartRepository;
import com.creaturelove.ecommercebackend.db.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, CartRepository cartRepository, UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(String username, String password) {
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        userDetailsManager.createUser(user);

        UserEntity savedUser = userRepository.findByUsername(username);
        CartEntity cart = new CartEntity();
        cart.setUserId(savedUser.getId());
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }

    public void getUserByUsername(String username) {
        userRepository.findByUsername(username);
    }

}
