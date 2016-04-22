/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itstep;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Liona
 */

@Controller
@RequestMapping("/session")
public class SessionController {
    
    @Autowired
    UserDao userDao;
    
    @ResponseBody
    @RequestMapping("/")
    public String showSession(HttpSession session){
        String user = "Not authorized";
        if(session.getAttribute("currentUser") != null){
            user = session.getAttribute("currentUser").toString();
        }
        return "Current user:" + user;
    }
    
    @ResponseBody
    @RequestMapping("/read")
    public String read(String var, HttpSession session){
        return session.getAttribute(var).toString();
    }
    
    @ResponseBody
    @RequestMapping("/write")
    public String write(String var, String message, HttpSession session){
        session.setAttribute(var, message);
        return session.getAttribute(var).toString();
    }
    
}
