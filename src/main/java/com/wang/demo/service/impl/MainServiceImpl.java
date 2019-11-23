package com.wang.demo.service.impl;

import com.wang.demo.dao.MainDao;
import com.wang.demo.entity.Article;
import com.wang.demo.entity.User;
import com.wang.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    MainDao mainDao;

    @Override
    public List<Article> getArticleList() {

        return mainDao.getArticleList();
    }

    @Override
    public Article getArticleById(int id) {
        return mainDao.getArticleById(id);
    }

    @Override
    public void deleteArticleById(int id) {
        mainDao.deleteArticleById(id);
    }

    @Override
    public void addArticle(Article article) {
        mainDao.addArticle(article);

    }

    @Override
    public void updateArticle(Article article) {
        mainDao.updateArticle(article);
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUser(int id) {
        return mainDao.getUser(id);
    }

}
