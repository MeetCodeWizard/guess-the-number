package com.meetcodewizard.guess_the_number.bean;

import java.time.LocalDateTime;

public class GameBean {
	private Integer log_id;
	private Integer userid;
	private LocalDateTime date;
	private Integer number_generated;
	private Integer number_guessed;
	private Boolean won;
	private Integer initial_credit;
	private Integer credit;
	private Integer final_credit;

	public Integer getLog_id() {
		return log_id;
	}

	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getNumber_generated() {
		return number_generated;
	}

	public void setNumber_generated(Integer number_generated) {
		this.number_generated = number_generated;
	}

	public Integer getNumber_guessed() {
		return number_guessed;
	}

	public void setNumber_guessed(Integer number_guessed) {
		this.number_guessed = number_guessed;
	}

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}

	public Integer getInitial_credit() {
		return initial_credit;
	}

	public void setInitial_credit(Integer initial_credit) {
		this.initial_credit = initial_credit;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getFinal_credit() {
		return final_credit;
	}

	public void setFinal_credit(Integer final_credit) {
		this.final_credit = final_credit;
	}

}
