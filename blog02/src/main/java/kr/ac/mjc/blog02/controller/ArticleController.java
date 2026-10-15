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
        ArticleResponse response=new ArticleResponse();
        String title=articleDto.getTitle();     //글제목
        if(title==null || title.equals("")){      //제목을 안썼을경우
            response.setSuccess(false);     //글쓰기 실패처리
            response.setMessage("제목을 입력해주세요");
            return ResponseEntity.ok(response);     //실패 응답 보내기
        }
        //글내용에 비속어가 있을경우 처리
        String body=articleDto.getBody();
        if(body.indexOf("시발")>-1){  //글 내용에 비속어가 있는경우
            response.setSuccess(false);
            response.setMessage("글 내용에 비속어를 쓸 수 없습니다");
            return ResponseEntity.ok(response);
        }


        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());  //DTO->Article 로 변형



        Article savedArticle=articleRepository.save(article);    //데이터베이스에 저장
        response.setSuccess(true);
        response.setArticle(savedArticle);

        return ResponseEntity.ok(response);
    }
}
