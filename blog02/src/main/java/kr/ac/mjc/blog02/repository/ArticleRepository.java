package kr.ac.mjc.blog02.repository;

import kr.ac.mjc.blog02.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {

    Article save(Article article);
}
