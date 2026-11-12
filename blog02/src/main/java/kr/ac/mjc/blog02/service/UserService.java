package kr.ac.mjc.blog02.service;

import kr.ac.mjc.blog02.dto.UserDto;
import kr.ac.mjc.blog02.dto.UserResponse;
import kr.ac.mjc.blog02.model.User;
import kr.ac.mjc.blog02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //회원 가입
    public UserResponse join(UserDto userDto){
        String email=userDto.getEmail();
        UserResponse response=new UserResponse();
        if(email==null|| email.isEmpty() ){ //이메일이 null 이거나 비어있으면
            response.setSuccess(false);
            response.setMessage("이메일이 비어있습니다");
            return response;
        }
        //이미 가입된 회원이 있는지 데이터 베이스에서 검색
        Optional<User> optional=userRepository.findOneByEmail(email);
        if( !optional.isEmpty()){    //이미 가입된 이메일이 있는경우
            response.setSuccess(false);
            response.setMessage("이미 가입된 이메일입니다");
            return response;
        }
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);      //데이터베이스에 회원정보 저장
        response.setSuccess(true);
        return response;

    }

    public UserResponse login(UserDto userDto){
        String email=userDto.getEmail();
        String password=userDto.getPassword();
        Optional<User> optional=userRepository.findOneByEmailAndPassword(email,password);
        UserResponse response=new UserResponse();
        if(optional.isEmpty()){     //이메일 또는 비밀번호가 틀린경우
            response.setSuccess(false);
            response.setMessage("이메일 또는 비밀번호가 틀립니다");
            return response;
        }

        response.setSuccess(true);
        response.setUser(optional.get());
        return response;
    }

    public User getUser(String email){
        Optional<User> optional=userRepository.findOneByEmail(email);
        if(optional.isEmpty()){
            return null;
        }
        else{
            return optional.get();
        }
    }
}
