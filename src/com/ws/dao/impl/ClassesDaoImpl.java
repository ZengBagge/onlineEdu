package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.ClassesDao;
import com.ws.po.Classes;

@Component("classesDao")
public class ClassesDaoImpl implements ClassesDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public List<Classes> getClassesByMajor(int id) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query =session.createQuery("From Classes c where c.major.id=? order by c.classUid ASC ");
			query.setInteger(0, id);
			@SuppressWarnings("unchecked")
			List<Classes> classes = query.list();
			return classes;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Classes getClassesByUid(String classUid) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("From Classes c where c.classUid=?");
            query.setString(0, classUid);
            if (query !=null && query.list() !=null && query.list().size()>0) {
				return (Classes) query.list().get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean save(Classes classes2) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
           session.save(classes2);
           return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean update(Classes classes) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
           session.update(classes);
           return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
