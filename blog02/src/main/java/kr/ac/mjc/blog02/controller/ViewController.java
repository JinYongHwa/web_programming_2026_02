package kr.ac.mjc.blog02.controller;

import kr.ac.mjc.blog02.model.Article;
import kr.ac.mjc.blog02.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @GetMapping("/view/{no}")
    public ModelAndView view(@PathVariable(name="no") long no){
        Article article=articleService.getItem(no);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("view");
        mav.addObject("article",article);
        return mav;
    }

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView mav=new ModelAndView();

        List<Article> articleList=articleService.getList();
        mav.setViewName("main");
        mav.addObject("list",articleList);
        return mav;
    }

    @GetMapping("/modify/{no}")
    public ModelAndView modify(@PathVariable("no") long no){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("modify");
        Article article=articleService.getItem(no);     //데이터베이스에 있는 글 가져오기
        mav.addObject("article",article);
        return mav;
    }




}
