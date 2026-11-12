package kr.ac.mjc.blog02.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog02.dto.ArticleDto;
import kr.ac.mjc.blog02.dto.ArticleResponse;
import kr.ac.mjc.blog02.model.Article;
import kr.ac.mjc.blog02.model.User;
import kr.ac.mjc.blog02.repository.ArticleRepository;
import kr.ac.mjc.blog02.service.ArticleService;
import kr.ac.mjc.blog02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @PostMapping("/api/article")
    public ResponseEntity<ArticleResponse> write(@RequestBody ArticleDto articleDto, HttpSession session){
        Article article=new Article();
        ArticleResponse response=new ArticleResponse();
        String title=articleDto.getTitle();     //글제목

        if(session.getAttribute("userId")==null){   //로그인 되어있지 않으면
            //글 작성 안되도록 처리
            response.setSuccess(false);
            response.setMessage("로그인 후 작성 가능합니다");
            return ResponseEntity.ok(response);
        }

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


        String email=(String)session.getAttribute("userId");
        User user=userService.getUser(email);
        article.setWriter(user);        //로그인된 사용자가 작성자로 지정
        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());  //DTO->Article 로 변형



        Article savedArticle=articleRepository.save(article);    //데이터베이스에 저장
        response.setSuccess(true);
        response.setArticle(savedArticle);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/article/{no}")
    public ResponseEntity<ArticleResponse> delete(@PathVariable("no") long no){
        boolean result=articleService.delete(no);   //삭제하고 성공여부를 반환받음
        ArticleResponse response=new ArticleResponse();
        response.setSuccess(result);        //성공여부 설정
        if(!result){        //삭제에 실패했을경우
            response.setMessage("존재하지 않는 글입니다");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/api/article/modify/{no}")
    public ResponseEntity<ArticleResponse> modify(@PathVariable("no") long no,@RequestBody ArticleDto articleDto){
        articleService.modify(no,articleDto);
        ArticleResponse response=new ArticleResponse();
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

}
