/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author student
 */
@Controller
public class UserController {
    @Autowired
    private UserDao userDao;


    // если пользователя нету возвращаем null
    @RequestMapping("/user")
    @ResponseBody
    public User issetNewUser(String name){
	try{ if( userDao.findByName(name).getName() != null ){ return this.userDao.findByName(name);} }
	catch(Exception e){ return null; }
	return null;
    }

}
