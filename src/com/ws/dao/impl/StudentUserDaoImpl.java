package com.ws.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.StudentUserDao;
import com.ws.po.StudentUser;

@Component("studentUserDao")
public class StudentUserDaoImpl implements StudentUserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public int getNumberByMajor(int majorId) throws Exception {
		int number = 0;
		Session session =null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("From StudentUser s where s.major.id=?");
			query.setInteger(0, majorId);
			number = query.list().size();
			System.out.println("专业"+majorId+"总人数："+number);
			return number;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public StudentUser getStudentUserByUserCommon(Long id) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from StudentUser s where s.userCommon.id=?");
			query.setLong(0, id);
			StudentUser studentUser =(StudentUser) query.list().get(0);
			return studentUser;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<StudentUser> getListByMajor(int id) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("From StudentUser s where s.major.id=? order by s.credit DESC ");
			query.setInteger(0, id);
			@SuppressWarnings("unchecked")
			List<StudentUser> studentUsers = query.list();
			Set<StudentUser>studentUsersSet = new HashSet<StudentUser>();
			for (StudentUser s:studentUsers) {
				studentUsersSet.add(s);
			}
			return studentUsersSet;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<StudentUser> getListByClasses(int id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("From StudentUser s where s.classes.id=? order by s.userCommon.loginTime DESC ");
			query.setInteger(0, id);
			List<StudentUser> studentUsers = query.list();
			Set<StudentUser>studentUsersSet = new HashSet<StudentUser>();
			for (StudentUser s:studentUsers) {
				studentUsersSet.add(s);
			}
			return studentUsersSet;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getNumberByClasses(int id) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("From StudentUser s where s.classes.id=? order by s.userCommon.loginTime DESC ");
			query.setInteger(0, id);
			return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean addStudentUser(StudentUser studentUser) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
            session.save(studentUser);
            return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
