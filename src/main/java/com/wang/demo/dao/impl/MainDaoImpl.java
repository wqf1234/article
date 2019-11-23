package com.wang.demo.dao.impl;

import com.wang.demo.dao.MainDao;
import com.wang.demo.entity.Article;

import com.wang.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public class MainDaoImpl implements MainDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> getArticleList() {
        return jdbcTemplate.query("select * from article where delete_flag = 0", new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public Article getArticleById(int id) {
        return jdbcTemplate.queryForObject("select * from article where delete_flag = 0", new BeanPropertyRowMapper<>(Article.class));

    }

    @Override
    public void deleteArticleById(int id) {
        jdbcTemplate.update("update article set delete_flag = ? where id = ?", 1, id);
    }

    @Override
    public void addArticle(Article article) {
        jdbcTemplate.update("insert into article (title, description, content) values (?, ?, ?)", article.getTitle(), article.getDescription(), article.getContent());

    }

    @Override
    public void updateArticle(Article article) {
        jdbcTemplate.update("update article set title = ?, description = ?, content = ? where id = ? and delete_flag = 0", article.getTitle(), article.getDescription(), article.getContent(), article.getId());
    }

    @Override
    public void register(User user){
        jdbcTemplate.update("insert into session(user_id, session_id) values (?, ?)",user.getId(),user.getPassword());
    }

    @Override
    public User getUser(int id) {
        return jdbcTemplate.queryForObject("select * from user where id = ?",new Object[]{id},new BeanPropertyRowMapper<>(User.class));
    }

}
