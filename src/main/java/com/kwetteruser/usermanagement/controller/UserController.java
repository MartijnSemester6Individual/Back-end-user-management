package com.kwetteruser.usermanagement.controller;

import com.kwetteruser.usermanagement.DTO.UserInfoDto;
import com.kwetteruser.usermanagement.entity.UserEntity;
import com.kwetteruser.usermanagement.messaging.RabbitMQSender;
import com.kwetteruser.usermanagement.service.AuthenticationFilter;
import com.kwetteruser.usermanagement.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    static final String ERROR_MESSAGE = "A problem occurred, while fetching the data";
    static final String ERROR_NAME = "Error";

    private UserService userService;

    @Autowired
    RabbitMQSender rabbitMQSender;

    public UserController(UserService userService) {
        this.userService = userService;
    }
//
//    @GetMapping("/users")
//    public List<UserEntity> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<UserEntity> GetUserById(@PathVariable("id") UUID id) {
//        UserEntity user = null;
//        user = userService.findById(id);
//        return ResponseEntity.ok(user);
//    }
//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        UUID userId = UUID.fromString(id);
        if(!userService.findById(userId).isPresent()) {
            return new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
        }
        userService.deleteById(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") UUID id,
//                                                 @RequestBody UserEntity user) {
//        user = userService.updateUser(id, user);
//        return ResponseEntity.ok(user);
//    }
    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateTag(@RequestBody UserInfoDto userInfoDto, @RequestHeader String id) {
        try {
            JSONObject updatedUser = new JSONObject();
            // If the ORM finds user with existing id, it just changes the different columns
            System.out.println(id + ' ' + userInfoDto.getId());
            if (id.equals(userInfoDto.getId().toString())) {
                UserEntity updated = userService.getUserById(userInfoDto.getId());
                updated.tag = (userInfoDto.getTag() == null) ? updated.tag : userInfoDto.getTag();
                updatedUser.put("id", id);
                updatedUser.put("tag", updated.tag);
                rabbitMQSender.send(updatedUser);
                return new ResponseEntity<>(userService.save(updated), HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest()
                        .header(ERROR_NAME, ERROR_MESSAGE)
                        .body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .header(ERROR_NAME, ERROR_MESSAGE)
                    .body(null);
        }
    }
}
