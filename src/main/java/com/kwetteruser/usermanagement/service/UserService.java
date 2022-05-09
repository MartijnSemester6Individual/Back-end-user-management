package com.kwetteruser.usermanagement.service;

import com.kwetteruser.usermanagement.DTO.UserInfoDto;
import com.kwetteruser.usermanagement.entity.UserEntity;
import com.kwetteruser.usermanagement.enumerators.Roles;
import com.kwetteruser.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> findAllByRole(Roles role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * This method is used to get all user information for backend checks.
     * @param userId
     * @return UserEntity
     */
    public UserEntity getUserById(UUID userId){
        return this.userRepository.findById(userId).get();
    }

    /**
     * This method is used to get user information for the frontend.
     * @param userId
     * @return userInfoDto without password and confirmation-code.
     */
    public UserInfoDto getUserInfo(UUID userId){
        UserEntity user =  this.userRepository.findById(userId).get();
        return new UserInfoDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
//                user.getTag(),
//                user.getBio(),
//                user.getWebsite(),
//                user.getLocation()
        );
    }

    /**
     * Saves user and returns user information for frontend.
     * @param user
     * @return UserInfoDto without password and confirmation-code.
     */
    public UserInfoDto saveUser(UserEntity user){
        UserEntity createdUser =  this.userRepository.save(user);
        return new UserInfoDto(
                createdUser.getId(),
                createdUser.getUsername(),
                createdUser.getEmail(),
                createdUser.getRole()
        );
    }
}
