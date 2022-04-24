package com.kwetteruser.usermanagement.controller;

import com.kwetteruser.usermanagement.DTO.LoginDto;
import com.kwetteruser.usermanagement.entity.UserEntity;
import com.kwetteruser.usermanagement.service.AuthService;
import com.kwetteruser.usermanagement.service.AuthenticationFilter;

import com.kwetteruser.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import java.util.Optional;
import java.util.StringTokenizer;

/**
 * Handles Authentication
 *
 * @class AuthController
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    private UserService userService;


    @PermitAll
    @PostMapping(value = "/login")
    public ResponseEntity authenticate(HttpServletResponse response,
                                       @RequestBody LoginDto loginDto) throws IOException, SQLException, URISyntaxException, NoSuchAlgorithmException {
        Optional<UserEntity> user = authService.getUser(loginDto.getEmail(), loginDto.getPassword());
        if (user.isEmpty()) {
            return new ResponseEntity<>("The email or password is wrong", HttpStatus.UNAUTHORIZED);
        }
        String userId = String.valueOf(user.get().getId());
        String token = authenticationFilter.createJWT(
                userId, user.get().getEmail(), user.get().getUsername(), user.get().getTag(), user.get().getRole(), -1
        );
        if (authenticationFilter.validateToken(token)) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("The email or password is wrong", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/verifyToken")
    public ResponseEntity VerifyToken(@RequestBody String body) {
        final StringTokenizer tokenizer = new StringTokenizer(body, ":");
        final String token = tokenizer.nextToken();

        if (authenticationFilter.validateToken(token)) {
            return new ResponseEntity<>("The token is valid", HttpStatus.OK);
        }
        return new ResponseEntity<>("The token is invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/register")
    // TODO @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    public ResponseEntity CreateUser(HttpServletResponse response,
                                     @RequestBody UserEntity user) throws IOException, SQLException, URISyntaxException, NoSuchAlgorithmException {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        if (user.getRole() == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Role is not valid!");
        }

        // Encrypt password using bcrypt
        user.password = AuthenticationFilter.getBcryptHash(user.password);
        try {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
