package com.wang.demo.service;

import com.wang.demo.entity.Session;

public interface SessionService {

    Session getSession(String uid);

    void addSession(int id, String randomString);
}
