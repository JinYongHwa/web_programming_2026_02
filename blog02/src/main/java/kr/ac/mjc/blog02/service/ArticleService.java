package kr.ac.mjc.blog02.service;

import kr.ac.mjc.blog02.model.Article;
import kr.ac.mjc.blog02.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article getItem(long no){
        return articleRepository.findById(no).get();
    }

    public List<Article> getList(){
        return articleRepository.findAll();
    }
}
