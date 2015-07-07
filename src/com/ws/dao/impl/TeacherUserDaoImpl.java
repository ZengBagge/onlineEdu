package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.TeacherUserDao;
import com.ws.po.TeacherUser;

@Component("teacherUserDao")
public class TeacherUserDaoImpl implements TeacherUserDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public TeacherUser getTeacherUserByUserId(Long id) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		TeacherUser teacherUser = null;

		try {
			session = sessionFactory.getCurrentSession();
			org.hibernate.Query q = session
					.createQuery("from TeacherUser t where t.userCommon.id=?");
			    q.setLong(0, id);
			teacherUser=(TeacherUser) q.uniqueResult();
			  return teacherUser;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherUser> getTeacherList() throws Exception {
		// TODO Auto-generated method stub
				Session session = null;

				try {
					session = sessionFactory.getCurrentSession();
					org.hibernate.Query q = session
							.createQuery("from TeacherUser t order by t.userCommon.loginTime DESC");

					  return q.list();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} 
	}
	@Override
	public boolean addTeacherUser(TeacherUser teacherUser) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
            session.save(teacherUser);
            return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
