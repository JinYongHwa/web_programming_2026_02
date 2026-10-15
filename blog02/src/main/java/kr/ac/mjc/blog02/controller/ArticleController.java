package kr.ac.mjc.blog02.controller;

import kr.ac.mjc.blog02.dto.ArticleDto;
import kr.ac.mjc.blog02.dto.ArticleResponse;
import kr.ac.mjc.blog02.model.Article;
import kr.ac.mjc.blog02.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @PostMapping("/api/article")
    public ResponseEntity<ArticleResponse> write(@RequestBody ArticleDto articleDto){
        Article article=new Article();
        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());  //DTO->Article 로 변형

        ArticleResponse response=new ArticleResponse();

        Article savedArticle=articleRepository.save(article);    //데이터베이스에 저장
        response.setSuccess(true);
        response.setArticle(savedArticle);

        return ResponseEntity.ok(response);
    }
}
