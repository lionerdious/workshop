/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.auth.controller;

/**
 *
 * @author lionel
 */
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import zw.co.psmi.workShop.auth.entity.User;


@Controller
@Slf4j
public class LoginController {
     @RequestMapping("/")
    public ModelAndView index(ModelAndView model) {
        SecurityContextHolder.clearContext();
        model.addObject("user", new User());
        
        model.setViewName("index");
        return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model, @ModelAttribute User user) {        
        model.setViewName("index");
        return model;
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model, @ModelAttribute User user) {        
        model.setViewName("home");
        return model;
    }

}
