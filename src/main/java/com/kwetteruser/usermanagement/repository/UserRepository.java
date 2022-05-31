package com.kwetteruser.usermanagement.repository;

import com.kwetteruser.usermanagement.entity.UserEntity;
import com.kwetteruser.usermanagement.enumerators.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAllByRole(Roles role);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findById(UUID uuid);
}
