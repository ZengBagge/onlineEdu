package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.QuestionTypeDao;
import com.ws.po.QuestionType;

@Component("questionTypeDao")
public class QuestionTypeDaoImpl implements QuestionTypeDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean insertType(QuestionType questionType) throws Exception {
		Session session = null;
		try{
		     	session =  sessionFactory.getCurrentSession(); 
			    session.saveOrUpdate(questionType);
			 return true;
		}catch(Exception e){e.printStackTrace();return false;}
			
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionType> getTypeList() {
		Session session =null;
		List<QuestionType> questionTypes;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("From QuestionType q order by q.id desc");
			questionTypes =query.list();
			if (questionTypes != null) {
				return questionTypes;
			}
			else {
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	@Override
	public QuestionType getQuestionTypeById(int id) {
		
		Session session = null;
		QuestionType questionType = null;

		try {
			session = sessionFactory.getCurrentSession();
			org.hibernate.Query q = session
					.createQuery("from QuestionType q where q.id=?");
			q.setInteger(0, id);
			questionType=(QuestionType) q.uniqueResult();
			  return questionType;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionType> getTypeListByCourseId(int course)
			throws Exception {
		Session session =null;
		List<QuestionType> questionTypes;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("From QuestionType as q inner join fetch q.couses c  where c.id=? order by q.id desc");
			query.setInteger(0, course);
			questionTypes =query.list();
			if (questionTypes != null) {
				return questionTypes;
			}
			else {
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	

}
