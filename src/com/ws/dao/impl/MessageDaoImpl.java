package com.ws.dao.impl;

import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.ws.dao.MessageDao;
import com.ws.po.Message;

@Component("messageDao")
public class MessageDaoImpl implements MessageDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public Message insert(Message message) throws Exception {
		
		Session session=null;
	     try {
			session= sessionFactory.getCurrentSession();
			 Long idInteger=(Long) session.save(message);
			 Message messageNew=(Message) session.get(Message.class, idInteger);
			 if (messageNew != null) {
				return messageNew;
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
