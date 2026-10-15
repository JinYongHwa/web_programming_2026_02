package kr.ac.mjc.blog02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/write")
    public String write(){
        return "write";
    }

}
