package com.ws.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.CheckAnswerDao;
import com.ws.po.CheckAnswer;

@Component("checkAnswerDao")
public class CheckAnswerDaoImpl implements CheckAnswerDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public CheckAnswer insert(CheckAnswer checkAnswer) throws Exception {
		 Session session =null;
		   try {
			session = sessionFactory.getCurrentSession();
			   Integer it = (Integer) session.save(checkAnswer);
			   if (it !=null)
			    {
				   return (CheckAnswer) session.get(CheckAnswer.class, it);
			    }
			   else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
