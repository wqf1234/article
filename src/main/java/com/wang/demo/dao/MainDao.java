package com.wang.demo.dao;

import com.wang.demo.entity.Article;
import com.wang.demo.entity.User;


import java.util.List;

public interface MainDao {
    List<Article> getArticleList();

    Article getArticleById(int id);

    void deleteArticleById(int id);

    void addArticle(Article article);

    void updateArticle(Article article);

    void register(User user);

    User getUser(int id);
}
