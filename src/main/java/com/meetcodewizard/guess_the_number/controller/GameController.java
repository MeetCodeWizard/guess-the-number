package com.meetcodewizard.guess_the_number.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.meetcodewizard.guess_the_number.bean.GameBean;
import com.meetcodewizard.guess_the_number.bean.SessionBean;
import com.meetcodewizard.guess_the_number.dao.GameDao;
import com.meetcodewizard.guess_the_number.dao.SessionDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {

	@Autowired
	GameDao gameDao;
	
	@Autowired
	SessionDao sessionDao;
	
	@GetMapping("/play")
	public String getPlayJSP(HttpSession session, Model model) {

		SessionBean sessionUser = (SessionBean) session.getAttribute("sessionUser");
		
		if(sessionUser == null) {
			model.addAttribute("loginRequest", "Please Login First !!!");
			return "Login";
		}
		
		SessionBean user = sessionDao.getUser(sessionUser.getUserid());
		
		sessionUser.setMaster_credit(user.getMaster_credit());
		
		Integer creditScore = user.getMaster_credit();
		model.addAttribute("creditScore", creditScore);

		return "Play";
	}

	@PostMapping("/guessNumberProcess")
	public String playGame(GameBean gameBean, Model model, HttpSession session) {

		SessionBean sessionUser = (SessionBean) session.getAttribute("sessionUser");
		
		// Credit Score
		Integer creditScore = sessionUser.getMaster_credit();
		
		if(creditScore <= 0) {
			model.addAttribute("creditLimitExceeded", "Please Buy Credits to Play Game !!!");
			return "Play";
		}
		
		// Set User Id in gameBean
		Integer userId = sessionUser.getUserid();
		gameBean.setUserid(userId);

		// Credit Earned
		Integer creditEarned = 0;

		// Get the Guessed Number
		Integer guessedNumber = gameBean.getNumber_guessed();

		// Generate the Random Number
		Random random = new Random();
		Integer randomNumber = random.nextInt(10) + 1;

		// Setting Generated Number
		gameBean.setNumber_generated(randomNumber);

		// Setting Guessed Number
		gameBean.setNumber_guessed(guessedNumber);

		// Validate Number
		if (guessedNumber.equals(randomNumber)) {
			// setting initial credits for gameBean
			gameBean.setInitial_credit(creditScore);

			// updating credits
			creditEarned += 100;
			creditScore += creditEarned;

			// setting credits to sessions master credit
			sessionUser.setMaster_credit(creditScore);

			// setting credits earned & updated credits to gameBean
			gameBean.setCredit(creditEarned);
			gameBean.setFinal_credit(creditScore);

			// setting winning status in gameBean
			gameBean.setWon(true);

		} else {
			// setting initial credits for gameBean
			gameBean.setInitial_credit(creditScore);

			// updating credits
			creditEarned -= 10;
			creditScore += creditEarned;

			// setting credits to sessions master credit
			sessionUser.setMaster_credit(creditScore);

			// setting credits earned & updated credits to gameBean
			gameBean.setCredit(creditEarned);
			gameBean.setFinal_credit(creditScore);

			// setting winning status in gameBean
			gameBean.setWon(false);
		}
		
		
		// Inserting Values into Userlogs from gameBean
		
		int statusGame = gameDao.createLog(gameBean);
		
		if(statusGame > 0) {
			System.out.println("Log Created");
		}else {
			System.out.println("Log Not Created");
		}
		
		// Updating Master Credit into Users from sessionBean
		int statusUser = sessionDao.updateMasterCredit(sessionUser);
		
		// Sending Data to JSP
		
		String result = gameBean.getWon() ? "You Won 👍" : "You Lost 👎";

		model.addAttribute("result", result);
		model.addAttribute("guessedNumber", guessedNumber);
		model.addAttribute("originalNumber", randomNumber);
		model.addAttribute("creditScore", creditScore);
		
		return "Play";
	}

}
