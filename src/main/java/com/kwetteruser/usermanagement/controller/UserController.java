package com.kwetteruser.usermanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

//    private UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
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
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") UUID id) {
//        boolean deleted = false;
//        deleted = userService.deleteUser(id);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", deleted);
//        return ResponseEntity.ok(response);
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") UUID id,
//                                           @RequestBody UserEntity user) {
//        user = userService.updateUser(id, user);
//        return ResponseEntity.ok(user);
//    }
}
