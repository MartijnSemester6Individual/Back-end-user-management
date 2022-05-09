package com.kwetteruser.usermanagement.DTO;

import com.kwetteruser.usermanagement.enumerators.Roles;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

public class UserInfoDto {

    public UserInfoDto(UUID id, String username, String email, Roles role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
//        this.tag = tag;
//        this.bio = bio;
//        this.website = website;
//        this.location = location;
    }

    @Getter @Setter
    private UUID id = UUID.randomUUID();
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private Roles role;
    @Getter @Setter
    private String tag;
    @Getter @Setter
    private String bio;
    @Getter @Setter
    private String website;
    @Getter @Setter
    private String location;

}
