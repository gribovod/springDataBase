/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itstep;

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
    
    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name){
        String id;
        User user = new User(name, email);
        userDao.save(user);
        id = String.valueOf(user.getId());
        return id;
    }
    @RequestMapping("/user")
    @ResponseBody
    public String findUserNameByEmail(String email){
        return userDao.findByEmail(email).getName();
    }
}
