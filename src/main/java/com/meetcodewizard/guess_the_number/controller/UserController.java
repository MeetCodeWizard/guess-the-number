package com.meetcodewizard.guess_the_number.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.meetcodewizard.guess_the_number.bean.FilterBean;
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

	@PostMapping("/filterProcess")
	public String getFilteredDashboard(FilterBean filterBean, Model model) {

		String filterType = filterBean.getFilterType();
		String filterValue = filterBean.getFilterValue();

		List<SessionBean> users = null;

		Boolean inputError = false;

		
		if (filterType == null) {
			model.addAttribute("filterSelectError", "Please Select Filter Type");
			inputError = true;
		}

		if (filterValue.trim().length() == 0) {
			model.addAttribute("filterInputError", "Please Enter Filter Value");
			inputError = true;
		}

		if (!inputError) {
			if (filterType.equals("stringField")) {
				// Correct Filter value
				users = sessionDao.filterUsersByName(filterValue);
			} else {
				Integer intFilterValue = null;
				try {
					// Parse Filter Value into Integer
					intFilterValue = Integer.parseInt(filterValue);
				} catch (NumberFormatException e) {
					model.addAttribute("invalidNumber", "Please Enter the Integer for Filtering with Comparison");
					inputError = true;
				}

				if(intFilterValue != null) {			
					if (filterType.equals("equals")) {
						users = sessionDao.filterUsersByCreditEquals(filterValue);
					} else if (filterType.equals("lessThan")) {
						users = sessionDao.filterUsersByCreditLess(filterValue);
					} else if (filterType.equals("greaterThan")) {
						users = sessionDao.filterUsersByCreditGreater(filterValue);
					}
				}
			}

			if(users != null)
				users.sort(Comparator.comparingInt(SessionBean::getMaster_credit).reversed());

		}

		model.addAttribute("filterType", filterBean.getFilterType());
		model.addAttribute("filterValue", filterBean.getFilterValue());
		model.addAttribute("users", users);

		return "Home";
	}

}
