package kr.ac.mjc.spring02;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/create")
    public String create(@PathParam("name") String name,
                         @PathParam("number") String number,
                         @PathParam("department") String department){

        Student student=new Student();
        student.setName(name);
        student.setNumber(number);
        student.setDepartment(department);

        studentRepository.save(student);

        return "생성완료";
    }
}
