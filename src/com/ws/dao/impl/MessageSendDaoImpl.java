package com.ws.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.ws.common.commonUtil;
import com.ws.dao.MessageSendDao;
import com.ws.po.MessageSendNote;
@Component("messageSendDao")
public class MessageSendDaoImpl implements MessageSendDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public int getAllRowRubbish(Long id) throws Exception {
		Session session = null;
		try {
			commonUtil.p("获取发件信息垃圾");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageSendNote m where m.del=1 and m.sender.id=? order by m.publishDate DESC ");
			query.setLong(0, id);
			return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getAllRow(Long id) throws Exception {
		
		Session session = null;
		try {
			commonUtil.p("获取发件信息总数");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageSendNote m where m.del=0 and m.sender.id=? order by m.publishDate DESC ");
			query.setLong(0, id);
			return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			MessageSendNote messageSendNote =(MessageSendNote) session.get(MessageSendNote.class, id);
			session.delete(messageSendNote);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendRubbish(Long id) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			MessageSendNote messageSendNote =(MessageSendNote) session.get(MessageSendNote.class, id);
			messageSendNote.setDel(1);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageSendNote> getRubbishList(int offset, int length, Long id) {
		Session session = null;
		try {
			commonUtil.p("获取发件信息垃圾列表");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageSendNote m where m.del=1 and m.sender.id=? order by m.publishDate DESC ");
			query.setLong(0, id);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(MessageSendNote messageSendNote) throws Exception {
		Session session=null;
		try {
			commonUtil.p("添加发件信息");
			session = sessionFactory.getCurrentSession();
            session.save(messageSendNote);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageSendNote> getList(int offset, int length, Long id)
			throws Exception {
		Session session = null;
		try {
			commonUtil.p("获取发件信息列表");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageSendNote m where m.del=0 and m.sender.id=? order by m.publishDate DESC ");
			query.setLong(0, id);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (HibernateException e) {
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
