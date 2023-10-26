package com.inbox.userListing.controller;

import com.inbox.userListing.dto.UserDto;
import com.inbox.userListing.entity.User;
import com.inbox.userListing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto requestMap){
        UserDto dto =  userService.signup(requestMap);
        return new ResponseEntity<UserDto>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dto = userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(dto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getuser(@PathVariable Integer id){
        User user = userService.getUser(id);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }
}
