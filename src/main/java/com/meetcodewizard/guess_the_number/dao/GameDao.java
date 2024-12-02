package com.meetcodewizard.guess_the_number.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.meetcodewizard.guess_the_number.bean.GameBean;

@Repository
public class GameDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int createLog(GameBean gameBean) {
		int rowsAffected = jdbcTemplate.update(" insert into Userlogs(userid, number_generated, number_guessed, won, initial_credit, credit, final_credit) values(?, ?, ?, ?, ?, ?, ?)", gameBean.getUserid(), gameBean.getNumber_generated(), gameBean.getNumber_guessed(), gameBean.getWon(), gameBean.getInitial_credit(), gameBean.getCredit(), gameBean.getFinal_credit());
		
		return rowsAffected;
	}
	
}
