package com.wang.demo.dao.impl;

import com.wang.demo.dao.SessionDao;
import com.wang.demo.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SessionDaoIml implements SessionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Session getSession(String uid) {
        System.out.println(uid);
        return jdbcTemplate.queryForObject("select * from session where user_id = ?",new Object[]{uid}, new BeanPropertyRowMapper<>(Session.class));
    }

    @Override
    public void addSession(int uid, String randomString) {
        jdbcTemplate.update("update session set session_id = ? where user_id = ?",new Object[]{randomString, uid});
    }
}
