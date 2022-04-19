package com.kwetteruser.usermanagement.DTO;

import com.kwetteruser.usermanagement.enumerators.Roles;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    private @Getter @Setter
    String username;
    private @Getter @Setter
    String email;
    private @Getter @Setter
    String password;
    private @Getter @Setter
    Roles role;

    public UserDto(String username, String email, String password, Roles role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
