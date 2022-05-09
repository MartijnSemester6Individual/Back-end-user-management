package com.kwetteruser.usermanagement.service;

import com.kwetteruser.usermanagement.entity.UserEntity;
import com.kwetteruser.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public Optional<UserEntity> getUser(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            Optional<UserEntity> user = userRepository.findByEmail(email);
            if (user.isPresent() && AuthenticationFilter.isPasswordValid(password, user.get().getPassword())) {
                return user;
            }
            return Optional.empty();
        }
        return Optional.empty();
    }
}
