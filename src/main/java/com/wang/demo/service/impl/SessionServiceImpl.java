package com.wang.demo.service.impl;

import com.wang.demo.dao.MainDao;
import com.wang.demo.dao.SessionDao;
import com.wang.demo.entity.Session;
import com.wang.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionDao sessionDao;

    @Override
    public Session getSession(String uid) {
        return sessionDao.getSession(uid);
    }

    @Override
    public void addSession(int uid, String randomString) {
        sessionDao.addSession(uid, randomString);
    }
}
