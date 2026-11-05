package kr.ac.mjc.blog02.repository;

import kr.ac.mjc.blog02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    public Optional<User> findOneByEmail(String email); //이메일로 가입된 회원 조회하기
    public User save(User user);        //데이터베이스에 회원정보 저장
    public Optional<User> findOneByEmailAndPassword(String email,String password);

}
