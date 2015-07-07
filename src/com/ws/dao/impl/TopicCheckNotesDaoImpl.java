package com.ws.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.TopicCheckNotesDao;
import com.ws.po.TopicCheckNotes;

@Component("topicCheckNotesDao")
public class TopicCheckNotesDaoImpl implements TopicCheckNotesDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public void save(TopicCheckNotes topicCheckNotes) throws Exception {
		 
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(topicCheckNotes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
