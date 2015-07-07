package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.MessageReceiveDao;
import com.ws.po.MessageReceiveNote;
@Component("messageReceiveDao")
public class MessageReceiveDaoImpl implements MessageReceiveDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public int getAllRowRubbish(Long id) throws Exception {
	
		Session session = null;
		try {
			commonUtil.p("获取收件信息垃圾");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=1 and m.receiver.id=? order by m.receiveDate DESC ");
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
			commonUtil.p("获取收件信息");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=0 and m.receiver.id=? order by m.receiveDate DESC ");
			query.setLong(0, id);
			int size=query.list().size();
			commonUtil.p("获取到收件信息的数量为"+size);
			return size;
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
			MessageReceiveNote messageReceiveNote =(MessageReceiveNote) session.get(MessageReceiveNote.class, id);
			session.delete(messageReceiveNote);
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
			MessageReceiveNote messageReceiveNote =(MessageReceiveNote) session.get(MessageReceiveNote.class, id);
			messageReceiveNote.setDel(1);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageReceiveNote> getRubbishList(int offset, int length,
			Long id) {
		Session session = null;
		try {
			commonUtil.p("获取收件信息垃圾列表");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=1 and m.receiver.id=? order by m.receiveDate DESC ");
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
	public void insert(MessageReceiveNote messageReceiveNote) throws Exception {
		Session session = null;
		try {
			commonUtil.p("添加收件信息");
			session = sessionFactory.getCurrentSession();
            session.save(messageReceiveNote);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageReceiveNote> getList(int offset, int length, Long id)
			throws Exception {
		Session session = null;
		try {
			commonUtil.p("获取收件信息");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=0 and m.receiver.id=? order by m.receiveDate DESC ");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageReceiveNote> getReceiveBySenderAndReceive(Long sender, long receiver) throws Exception {
		Session session = null;
		try {
			commonUtil.p("获取收件信息，消息列表");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=0 and m.receiver.id=? and m.sender=? and m.readed=0 order by m.receiveDate ASC ");
			query.setLong(0, sender);
			query.setLong(1, receiver);
			if (query.list().size()>0) {
				return query.list();
			}else {
				Query query2 =session.createQuery("From MessageReceiveNote m where m.del=0 and m.receiver.id=? and m.sender=? order by m.receiveDate ASC ");
				query2.setLong(0, sender);
				query2.setLong(1, receiver);
				query2.setFirstResult(1);
				query2.setMaxResults(3);
				return query2.list();
			}
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

	@Override
	public int getNewReceiveNumber(Long id) throws Exception {
		Session session = null;
		try {
			commonUtil.p("获取收件箱新信息");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=0 and m.readed=0  and m.receiver.id=? order by m.receiveDate DESC ");
			query.setLong(0, id);
			return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取某发送者的新消息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageReceiveNote> getNewReceiveList(long senderId,long receiverId)
			throws Exception {
		Session session = null;
		try {
			commonUtil.p("获取收件箱新信息");
			session =sessionFactory.getCurrentSession();
			Query query =session.createQuery("From MessageReceiveNote m where m.del=0 and m.readed=0 and m.receiver.id=?  and m.sender.id=? order by m.receiveDate DESC ");
			query.setLong(0, senderId);
			query.setLong(1, receiverId);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(MessageReceiveNote messageReceiveNote)
			throws Exception {
		Session session= null;
		try {
			session =sessionFactory.getCurrentSession();
			session.update(messageReceiveNote);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
}
