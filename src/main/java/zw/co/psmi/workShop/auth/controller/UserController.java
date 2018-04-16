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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.psmi.workShop.auth.entity.Login;
import zw.co.psmi.workShop.auth.entity.User;
import zw.co.psmi.workShop.auth.service.UserService;

@Controller
public class UserController {
    private UserService userService;
    //Create user
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } 

	
	 @RequestMapping(value = "/admin/users", method = RequestMethod.GET) 
	 public String users(@AuthenticationPrincipal Login login, Model model) {
	        model.addAttribute("userss", this.userService.findAll());	
			return "/admin/users";
		}
	 
	 
	 @RequestMapping(value = "/admin/createuser", method = RequestMethod.GET)
		public String createuser(@ModelAttribute User user,  Model model) {       
		 //user.setDepartment(departmentService.getAll());
		 model.addAttribute("users", user);
		 return "/admin/createuser";
		}
	 
	 
	  @RequestMapping(value = "/admin/createuserform", method = RequestMethod.POST)
	    public String createuserform( @ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
		  String msg = this.userService.save(user);
	      redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
	          return "redirect:/admin/users";
	    }
}
