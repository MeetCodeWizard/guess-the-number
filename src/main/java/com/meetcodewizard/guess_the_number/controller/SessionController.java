package com.meetcodewizard.guess_the_number.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String saveUser(SessionBean sessionBean) {
		int status = sessionDao.createUser(sessionBean);
		if(status == 0) {
			System.out.println("User Not Created");
		}else {
			System.out.println("User Created");
		}
		return "Login";
	}
	
	@PostMapping("/loginProcess")
	public String validateUser(SessionBean sessionBean, Model model, HttpSession session) {
		
		SessionBean user = sessionDao.getUser(sessionBean);
		
		if(user == null) {
            model.addAttribute("error", "Invalid email or password");
			return "redirect:/login";
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
