package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.DiscuzDao;
import com.ws.po.DiscuzMessage;

@Component("discuzDao")
public class DiscuzDaoImpl implements DiscuzDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public int getAllRow(int tid) throws Exception {
		
		Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from DiscuzMessage  d where d.topic.id=?");
			   query.setInteger(0, tid);  
			   return query.list().size();
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscuzMessage> getList(int offset, int length, int tid) {
		 List<DiscuzMessage> lists =null;
		   Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from DiscuzMessage d where d.topic.id=? order by d.sort DESC");
			   query.setInteger(0, tid);
			   query.setFirstResult(offset);
			   query.setMaxResults(length);
			   lists=query.list();
			   return lists;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DiscuzMessage insert(DiscuzMessage discuzMessage) {
	
		  Session session =null;
		  try {
			session =sessionFactory.getCurrentSession();
			  long id=(Long) session.save(discuzMessage);
			  if(id != 0){
				  return (DiscuzMessage) session.get(DiscuzMessage.class, id);
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

	@Override
	public boolean delInvitation(long aid) {
		
		Session session =null;
		
		try {
			session = sessionFactory.getCurrentSession();
			DiscuzMessage discuzMessage = (DiscuzMessage) session.get(DiscuzMessage.class, aid);
			session.delete(discuzMessage);
			commonUtil.p("消息被赞一次");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscuzMessage> getListByCourse(int offset, int length, int cid) {
		 List<DiscuzMessage> lists =null;
		   Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from DiscuzMessage d where d.course.id=? order by d.sort DESC");
			   query.setInteger(0, cid);
			   query.setFirstResult(offset);
			   query.setMaxResults(length);
			   lists=query.list();
			   return lists;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getAllRowByCourse(int cid) {
		Session session =null;
		   try {
			session=sessionFactory.getCurrentSession();
			   Query query =session.createQuery("from DiscuzMessage  d where d.course.id=?");
			   query.setInteger(0, cid);  
			   return query.list().size();
			   
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public DiscuzMessage getDiscuzMessage(long id) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			DiscuzMessage discuzMessage = (DiscuzMessage) session.get(DiscuzMessage.class, id);
			return discuzMessage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean Update(DiscuzMessage discuzMessage) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(discuzMessage);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
