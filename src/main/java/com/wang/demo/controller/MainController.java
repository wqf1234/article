package com.wang.demo.controller;


import com.wang.demo.entity.Article;
import com.wang.demo.entity.User;
import com.wang.demo.service.MainService;
import com.wang.demo.service.SessionService;
import com.wang.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @Autowired
    SessionService sessionService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    /**
     *
     * 拦截器或者过滤器 ！！！！！！！！！！！！
     *
     * 使用拦截器或者过滤器实现
     *
     * 登录
     * String sessionID = httpCookie.getValue("session_id");
     * String rightSessionID = sessionDao.get("uid");
     * return sessionID.equals(rightSessionID);
     *
     * 注销
     * httpCookie.remove("session_id");
     *
     * jdbc  MyBatis
     *
     * token
     *
     * Shiro  身份验证 权限管理
     *
     * Spring Security
     *
     * JWT Json Web Token
     */

    @RequestMapping(value = "/login")
    public String userLogin(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/loginFun")
    public String login(@ModelAttribute(value = "user") User user,
                        HttpServletResponse response){
        User rightUser = mainService.getUser(user.getId());
        if(!user.getPassword().equals(rightUser.getPassword())){
            return "login";
        }
        String randomString = Util.generateRandomString();
        sessionService.addSession(user.getId(), randomString);
        Cookie sessionId = new Cookie("sessionId", randomString);
        Cookie uid = new Cookie("uid", String.valueOf(user.getId()));
        sessionId.setMaxAge(5 * 60 * 1000);
        uid.setMaxAge(5 * 60 * 1000);
        response.addCookie(sessionId);
        response.addCookie(uid);
        return "index";
    }

    @RequestMapping(value = "/isLogin")
    public String isLogin(Model model, @CookieValue("sessionId") String sessionId, @CookieValue("uid") String uid){
        if("".equals(sessionId) || "".equals(uid)){
            System.out.println("uid = " + uid);
            System.out.println("sessionId = " + sessionId);
            System.out.println("-----------error 1------------");
            model.addAttribute("user", new User());
            return "login";
        }
        String rightSessionId = sessionService.getSession(uid).getSessionId();
        if (!sessionId.equals(rightSessionId)){
            System.out.println("-----------error 2------------");
            model.addAttribute("user", new User());
            return "login";
        }
        return  "index";
    }

    @RequestMapping(value = "/logout")
    public String remove(HttpServletResponse response){
        Cookie sessionId = new Cookie("sessionId", "");
        sessionId.setMaxAge(5 * 60 * 1000);
        response.addCookie(sessionId);
        return "index";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        model.addAttribute("article", new Article());
        return "add_page";
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String getArticleList(Model model) {
        List<Article> list = new ArrayList<>();
        list = mainService.getArticleList();
        System.out.println(list);
        model.addAttribute("articles", list);
        return "articles";
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public String getArticle(Model model, @PathVariable("id") int id) {
        Article article = mainService.getArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }

    @RequestMapping(value = "/deleteArticle/{id}", method = RequestMethod.GET)
    public String deleteArticle(Model model, @PathVariable("id") int id) {
        mainService.deleteArticleById(id);
        return "delete_success";
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(Model model, @ModelAttribute(value = "article") Article article) {
        mainService.addArticle(article);
        return "add_success";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable("id") int id) {
        Article article = mainService.getArticleById(id);
        model.addAttribute("article", article);
        return "update";

    }

    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public String updateArticle(Model model,
                                @ModelAttribute(value = "article") Article article) {
        mainService.updateArticle(article);
        return "update_success";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public String registerUser(Model model,
                               @ModelAttribute(value = "user") User user){
        mainService.addUser(user);
        return "index";
    }
}
