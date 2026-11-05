package kr.ac.mjc.blog02.controller;

import kr.ac.mjc.blog02.dto.UserDto;
import kr.ac.mjc.blog02.dto.UserResponse;
import kr.ac.mjc.blog02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/join")
    public ResponseEntity<UserResponse> join(@RequestBody UserDto userDto){
        UserResponse response=userService.join(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserDto userDto){
        UserResponse response= userService.login(userDto);
        return ResponseEntity.ok(response);
    }
}
