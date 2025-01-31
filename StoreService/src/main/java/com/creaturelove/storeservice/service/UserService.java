package com.creaturelove.storeservice.service;

import com.creaturelove.storeservice.db.entity.CartEntity;
import com.creaturelove.storeservice.db.entity.UserEntity;
import com.creaturelove.storeservice.db.repository.CartRepository;
import com.creaturelove.storeservice.db.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

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

        Optional<UserEntity> savedUser = userRepository.findByUsername(username);
        CartEntity cart = new CartEntity();
        cart.setUser(savedUser.get());
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    public UserEntity getUserByUsername(String username) throws Exception {
        Optional <UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }else{
            throw new Exception("User not found");
        }
    }

}
