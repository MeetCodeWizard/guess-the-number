package com.meetcodewizard.guess_the_number.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meetcodewizard.guess_the_number.bean.SessionBean;
import com.meetcodewizard.guess_the_number.dao.SessionDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	SessionDao sessionDao;
	
	

	@GetMapping("/home")
	public String getHomePage(HttpSession session, Model model) {

		SessionBean sessionUser = (SessionBean) session.getAttribute("sessionUser");

		if (sessionUser == null) {
			model.addAttribute("loginRequest", "Please Login First !!!");
			return "Login";
		}

		List<SessionBean> users = sessionDao.getAllUsers();

		if (users != null) {
			model.addAttribute("users", users);
		} else {
			System.out.println("No Users Found");
		}

		users.sort(Comparator.comparingInt(SessionBean::getMaster_credit).reversed());

		return "Home";
	}

}
