package kr.ac.mjc.blog02.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.blog02.dto.ArticleDto;
import kr.ac.mjc.blog02.model.Article;
import kr.ac.mjc.blog02.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article getItem(long no){
        Optional<Article> optional=articleRepository.findById(no);
        if(optional.isEmpty()){     //글이 없을경우
            return null;
        }
        return optional.get();      //글이 있을경우 Article 리턴
    }

    public List<Article> getList(){
        return articleRepository.findAll();
    }

    //글번호를 받아서 글이 있으면 삭제
    public boolean delete(long no){
        Article article=getItem(no);        //글을 데이터베이스에서 가져온다
        if(article==null){  //글이 없으면
            return false;
        }
        articleRepository.deleteById(no);   //글 삭제
        return true;
    }

    //글 수정하기
    @Transactional
    public boolean modify(long no, ArticleDto articleDto){
        Article article=getItem(no);    //데이터베이스에서 글 가져오기

        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());
        return true;
    }

}
