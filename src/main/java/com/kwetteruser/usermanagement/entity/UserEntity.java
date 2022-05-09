package com.kwetteruser.usermanagement.entity;

import com.kwetteruser.usermanagement.enumerators.Roles;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name="id", nullable = false, unique = true)
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(nullable = false, unique = true)
    @NonNull
    public String username;

    @Column(nullable = false, unique = true)
    @NonNull
    public String tag;

    @Column(unique = true)
    public String email;

    @Column()
    public String bio;

    @Column()
    public String website;

    @Column()
    public String location;

    @Column()
    public Roles role;

    @Column(nullable = false)
    public String password;


}
