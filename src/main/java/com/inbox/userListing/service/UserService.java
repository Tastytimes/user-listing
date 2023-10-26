package com.inbox.userListing.service;

import com.inbox.userListing.dto.UserDto;
import com.inbox.userListing.entity.User;
import com.inbox.userListing.repo.UserRepo;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepo userRepo;


    public UserDto signup(UserDto requestMap ){
        User user = new User();
        UserDto dto = new UserDto();
        user.setName(requestMap.getName());
        user.setEmail(requestMap.getEmail());
        user.setAddress(requestMap.getAddress());
        user.setCity(requestMap.getCity());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode(requestMap.getPassword());
        boolean passwordMatch = encoder.matches("123456", result);

//        Boolean result1 = assertTrue(encoder.matches(requestMap.getPassword(), result));
        log.info("encoded password {}", result);
        log.info("decode password {}", passwordMatch);
        user.setPassword(result);
        User resp = userRepo.save(user);
        dto.setName(resp.getName());
        dto.setEmail(resp.getEmail());
        dto.setAddress(resp.getAddress());
        dto.setCity(resp.getCity());
        dto.setId(resp.getId());
        return dto;
    }

    public List<UserDto> getAllUsers() {
        List<User> userResp = userRepo.findAll();
        List<UserDto> userList = new ArrayList<UserDto>();
        for(User user : userResp ) {
            UserDto dto = new UserDto();
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setAddress(user.getAddress());
            dto.setCity(user.getCity());
            dto.setId(user.getId());
            userList.add(dto);
        }
    return userList;
    }

    public User getUser(Integer id) {
        Optional<User> optional = userRepo.findById(id);
        User user = optional.orElse(null);
        return user;
    }
}
