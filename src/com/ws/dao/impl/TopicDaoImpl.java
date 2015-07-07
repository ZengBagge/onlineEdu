package com.ws.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.TopicDao;
import com.ws.po.Topic;

@Component("topicDao")
public class TopicDaoImpl implements TopicDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public Topic insetTopic(Topic topic) throws Exception {
		
		Session session=null;
	     try {
			session= sessionFactory.getCurrentSession();
			 Long id=(Long) session.save(topic);
			 commonUtil.p("添加题目公告信息");
			 Topic topicNewTopic=(Topic) session.get(Topic.class, id);
			 if (topicNewTopic != null) {
				return topicNewTopic;
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopics(int courseId) throws Exception {
		
		List<Topic>topics =null;
		Session session =null;
		try {
			session=sessionFactory.getCurrentSession();
			org.hibernate.Query  query= session.createQuery("from Topic t where t.course.id=? order by t.sum desc");
			query.setInteger(0, courseId);
			topics=query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			topics=null;
		}
		return topics;
	}

	@Override
	public Topic getTopicById(long topicId) throws Exception {
	
		Session session =null;
		try {
			session =sessionFactory.getCurrentSession();
			return (Topic) session.get(Topic.class, topicId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void addsum(long topic) throws Exception {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Topic topic2=(Topic) session.get(Topic.class, topic);
			topic2.setSum(topic2.getSum()+1);
			
			commonUtil.p("练习次数加一");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addError(long topic) throws Exception {

			Session session = null;
			try {
				session = sessionFactory.getCurrentSession();
				Topic topic2=(Topic) session.get(Topic.class, topic);
				topic2.setError(topic2.getError()+1);
				commonUtil.p("错误次数加一");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				
	}

	@Override
	public List<Topic> getTopicByRandAndCourseId(int type, int number,
			int courseId) throws Exception {
		
		List<Topic>topics =new ArrayList<Topic>();
		Session session =null;
		try {
			session=sessionFactory.getCurrentSession();
			org.hibernate.Query  query= session.createQuery("from Topic t where t.course.id=? and t.type.id=? ");
			commonUtil.p(query.getQueryString());
			query.setInteger(0, courseId);
			query.setInteger(1, type);
			int maxSize=query.list().size();
			  if(maxSize>0){
					commonUtil.p("查询到相关题目"+maxSize+"道");
					Set<Integer>set = new HashSet<Integer>();
					for (int i=0;i<number;i++) {
						   int randNumber = (int) (Math.random() * maxSize + 1) - 1;
						   set.add(randNumber);
					}   
					commonUtil.p("随机数个数"+set.size());
					for (Integer a:set) {
						Topic topic=(Topic) query.list().get(a);
						topics.add(topic);
					}
			  }else {
				  commonUtil.p("查询到相关题目0道");
				return null;
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			topics=null;
		}
		commonUtil.p("查询到随机题目"+topics.size()+"道");
		return topics;
		
	}

	
}
