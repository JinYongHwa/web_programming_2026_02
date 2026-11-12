package kr.ac.mjc.blog02.controller;

import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<UserResponse> login(@RequestBody UserDto userDto, HttpSession session){
        UserResponse response= userService.login(userDto);

        if(response.isSuccess()){   //로그인이 성공했을경우
            String email=response.getUser().getEmail();
            session.setAttribute("userId",email);
        }

        return ResponseEntity.ok(response);
    }
}
