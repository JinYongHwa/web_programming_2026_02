package kr.ac.mjc.blog02.dto;

import kr.ac.mjc.blog02.model.Article;

public class ArticleResponse {
    private boolean success;    //성공여부
    private Article article;    //성공했을때의 article 객체
    private String message;     //실패시 메세지

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
