package com.wang.demo.dao;

import com.wang.demo.entity.Session;

public interface SessionDao {
    Session getSession(String uid);

    void addSession(int uid, String randomString);
}
