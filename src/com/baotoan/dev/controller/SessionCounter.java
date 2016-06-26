package com.baotoan.dev.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.baotoan.dev.dao.StatisticDAO;
import com.baotoan.dev.service.StatisticDAOImpl;

public class SessionCounter implements HttpSessionListener {
	private StatisticDAO statistic = new StatisticDAOImpl();
	private Set<String> sessions = new HashSet<>();
	int totalVistor = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		totalVistor = statistic.update(e.getSession());
		sessions.add(e.getSession().getId());
		e.getSession().setAttribute("counter", this);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		sessions.remove(e.getSession().getId());
		e.getSession().setAttribute("counter", this);
	}
	
	public int getTotalVistor() {
		return totalVistor;
	}
	
	public int getStatistic() {
		return sessions.size();
	}


}
