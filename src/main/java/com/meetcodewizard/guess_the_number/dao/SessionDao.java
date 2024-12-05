package com.meetcodewizard.guess_the_number.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.meetcodewizard.guess_the_number.bean.SessionBean;

@Repository
public class SessionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int createUser(SessionBean sessionBean) {
		int status = jdbcTemplate.update("insert into Users (firstname,email,password) values (?,?,?)",
				sessionBean.getFirstname(), sessionBean.getEmail(), sessionBean.getPassword());
		return status;
	}

	public SessionBean getUser(SessionBean sessionBean) {
		SessionBean user;
		try {
			user = jdbcTemplate.queryForObject("select * from Users where email=? and password=?",
					new BeanPropertyRowMapper<>(SessionBean.class), sessionBean.getEmail(), sessionBean.getPassword());
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public List<SessionBean> getAllUsers() {
		try {
			List<SessionBean> users = jdbcTemplate.query("select * from Users",
					new BeanPropertyRowMapper<>(SessionBean.class));
			return users;
		} catch (Exception e) {
			return null;
		}
	}

	public int updateMasterCredit(SessionBean sessionUser) {
		int rowsAffected = jdbcTemplate.update("update Users set master_credit=? where userid=?",
				sessionUser.getMaster_credit(), sessionUser.getUserid());
		return rowsAffected;
	}

	public SessionBean getUser(Integer userId) {
		SessionBean user = jdbcTemplate.queryForObject("select * from Users where userid=?",
				new BeanPropertyRowMapper<>(SessionBean.class), new Object[] { userId });
		return user;
	}

	public List<SessionBean> filterUsersByName(String filterValue) {
		try {
			List<SessionBean> users = jdbcTemplate.query("select * from Users where firstname like ?",
					new BeanPropertyRowMapper<>(SessionBean.class), new Object[] { "%" + filterValue + "%" });
			return users;
		} catch (Exception e) {
			return null;
		}
	}

	public List<SessionBean> filterUsersByCreditEquals(String filterValue) {
		try {
			List<SessionBean> users = jdbcTemplate.query("select * from Users where master_credit=?",
					new BeanPropertyRowMapper<>(SessionBean.class), new Object[] { filterValue });
			return users;
		} catch (Exception e) {
			return null;
		}
	}

	public List<SessionBean> filterUsersByCreditLess(String filterValue) {
		try {
			List<SessionBean> users = jdbcTemplate.query("select * from Users where master_credit<?",
					new BeanPropertyRowMapper<>(SessionBean.class), new Object[] { filterValue });
			return users;
		} catch (Exception e) {
			return null;
		}
	}

	public List<SessionBean> filterUsersByCreditGreater(String filterValue) {
		try {
			List<SessionBean> users = jdbcTemplate.query("select * from Users where master_credit>?",
					new BeanPropertyRowMapper<>(SessionBean.class), new Object[] { filterValue });
			return users;
		} catch (Exception e) {
			return null;
		}
	}
}
