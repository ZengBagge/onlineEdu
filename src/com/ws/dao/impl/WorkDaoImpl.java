package com.ws.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.WorkDao;
import com.ws.po.Work;

@Component("workDao")
public class WorkDaoImpl implements WorkDao {

	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Work> getWorking(int courseId, Date date) throws Exception {
		Session session=null;
		List<Work>works=null;
		try {
			session = sessionFactory.openSession();
			Query query =session.createQuery("from Work w where w.endDate>? and w.course.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, courseId);
			works=query.list();
			return works;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			if(session != null)
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> getWorked(int courseId, Date date) throws Exception {
		Session session=null;
		List<Work>works=null;
		try {
			session = sessionFactory.openSession();
			Query query =session.createQuery("from Work w where w.endDate<? and w.course.id=? order by w.startDate asc");
			query.setDate(0, date);
			query.setInteger(1, courseId);
			works=query.list();
			return works;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			if(session != null)
				session.close();
		}
	}

	@Override
	public Work getWorkById(int workId) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Work work=(Work) session.get(Work.class, workId);
			return work;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			if(session != null)
				session.close();
		}
	}

	@Override
	public void setWan(Work work) throws Exception {
	
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Work work2=(Work) session.get(Work.class, work.getId());
			work2.setWan(work2.getWan()+1);
			session.getTransaction().commit();
			commonUtil.p("任务+"+work2.getTitleString()+"完成人数加一");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(session !=null){
				session.close();
			}
		}
		
	}

}
