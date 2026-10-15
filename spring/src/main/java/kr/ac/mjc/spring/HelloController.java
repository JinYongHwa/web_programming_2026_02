package kr.ac.mjc.spring;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(){
        Student student=new Student();
        return "안녕하세요 진용화입니다";
    }
    @GetMapping("/students")
    public ResponseEntity< ArrayList<Student> >  studentList(@PathParam("name") String name,@PathParam("count") int count){
        ArrayList<Student> list=new ArrayList<Student>();
        for(int i=0;i<count;i++){
            Student student=new Student();
            student.setName(name+i);
            student.setNumber("20262610"+i);
            student.setDepartment("정보통신공학과");
            list.add(student);  //배열에 학생 넣기
        }   //학생 10명이 들어가있는 배열

        return ResponseEntity.ok(list);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

}
