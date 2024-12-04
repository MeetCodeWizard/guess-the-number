package com.meetcodewizard.guess_the_number.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.meetcodewizard.guess_the_number.bean.SessionBean;
import com.meetcodewizard.guess_the_number.dao.SessionDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {

	@Autowired
	SessionDao sessionDao;
	
	@GetMapping("/signup")
	public String getSignupPage() {
		return "Signup";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "Login";
	}
	
	@PostMapping("/signupProcess")
	public String saveUser(@Validated SessionBean sessionBean, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
//			result.getFieldError("firstname").getDefaultMessage();
			model.addAttribute("result", result);
			model.addAttribute("userState", sessionBean);
			return "Signup";
		}
		
		int status = sessionDao.createUser(sessionBean);
		if(status == 0) {
			System.out.println("User Not Created");
		}else {
			System.out.println("User Created");
		}
		return "redirect:/login";
	}
	
	@PostMapping("/loginProcess")
	public String validateUser(@Validated SessionBean sessionBean, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		
		SessionBean user = sessionDao.getUser(sessionBean);

		if(result.hasErrors() && user == null) {
			model.addAttribute("result", result);
			model.addAttribute("userState", sessionBean);
			return "Login";
		}
		
		
		if(user == null) {
            model.addAttribute("loginError", "Invalid email or password");
			return "Login";
		}

		session.setAttribute("sessionUser", user);
		
		return "redirect:/home";
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}
