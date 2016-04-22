/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itstep;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Liona
 */

@RestController
public class AuthorizationController {
    
    @Autowired
    UserDao userDao;
    
    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password){
        if(userDao.findByName(username) != null){
            if(userDao.findByName(username).password.equals(password)){
                session.setAttribute("currentUser", username);
                return "Successfull";
            }
            return "Login failed. Incorrect password";
        }
        return "Login failed. No such user!";
    }
    
    @ResponseBody
    @RequestMapping("/register")
    public String register(String username, String password){
        User newUser;
        try{
            newUser = new User(username, password);
            userDao.save(newUser);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        return "User " + newUser.getName() + " was successfully registered.";
    }
}
