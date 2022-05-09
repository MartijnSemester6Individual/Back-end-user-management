package com.kwetteruser.usermanagement.service;
import com.kwetteruser.usermanagement.DTO.UserInfoDto;
import com.kwetteruser.usermanagement.entity.UserEntity;
import com.kwetteruser.usermanagement.enumerators.Roles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserInterface {
    List<UserEntity> findAll();
    List<UserEntity> findAllByRole(Roles role);
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(long id);
    void deleteById(long id);
    void deleteAll();
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);



}
