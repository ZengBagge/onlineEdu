package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.ExamQuestionTypeDao;
import com.ws.po.ExamQuestionType;


@Component("examQuestionTypeDao")
public class ExamQuestionTypeDaoImpl implements ExamQuestionTypeDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean insert(ExamQuestionType examQuestionType) throws Exception {
		
		Session session =null;
		try {
			session = sessionFactory.getCurrentSession();
			Integer id =(Integer) session.save(examQuestionType);
           if (id!= null) {
			return true;
		}else {
			return false;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamQuestionType> getListByExam(int examTopicId) throws Exception {
		Session session =null;
		List<ExamQuestionType> questionTypes;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("From ExamQuestionType q where q.examTopic.id=? order by q.id desc");
			query.setInteger(0, examTopicId);
			questionTypes =query.list();
		    return questionTypes;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean del(int id) throws Exception {
		// TODO Auto-generated method stub
		Session session= null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query=session.createQuery("From ExamQuestionType q where q.examTopic.id=? order by q.id desc");
			query.setInteger(0, id);
			@SuppressWarnings("unchecked")
			List<ExamQuestionType>questionTypes =query.list();
			commonUtil.p("删除考试组卷题型信息");
			for(ExamQuestionType e:questionTypes){
	           session.delete(e);
			}
	          session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
}
