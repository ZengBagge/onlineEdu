package com.ws.dao.impl;



import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.ws.common.commonUtil;
import com.ws.dao.UserDao;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUserCommon(UserCommon userCommon) throws Exception {
		
		  System.out.print("成功增加一个用户："+userCommon.getUname());
			Session session = null;
			try{
				    session =  sessionFactory.getCurrentSession(); 
				    session.save(userCommon);
				    return true;
			}catch(Exception e){e.printStackTrace();return false;}
		
	}

	@Override
	public boolean delete(long id) throws Exception {
		
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			UserCommon userCommon =(UserCommon) session.get(UserCommon.class, id);  //取回对象
            session.delete(userCommon);  //删除对象
            return true;
		}catch(Exception e){e.printStackTrace();return false;}
		
		
	}


	@Override
	public boolean updata(UserCommon userCommon) throws Exception {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userCommon);
			commonUtil.p("更新用户信息");
			return true;
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

	@Override
	public StudentUser getStudentById(long id) throws Exception {
		// TODO Auto-generated method stub
		StudentUser studentUser=null;
		Session session=null;
		try {
			session =  sessionFactory.getCurrentSession(); 
			org.hibernate.Query q= session.createQuery("from StudentUser u where u.userCommon.id=?"); 
			 q.setLong(0, id);
			 studentUser= (StudentUser) q.uniqueResult();
			 return studentUser;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public TeacherUser getTeacherById(long id) throws Exception {
		// TODO Auto-generated method stub
		TeacherUser teacherUser=null;
		Session session=null;
		try {
			session =  sessionFactory.getCurrentSession(); 
			org.hibernate.Query q= session.createQuery("from TeacherUser u where u.userCommon.id=?"); 
			 q.setLong(0, id);
			 teacherUser= (TeacherUser) q.uniqueResult();
			 return teacherUser;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changePassById(String pwd, String uid, String newPwd) {
		
		UserCommon user = new UserCommon();
		Session session = null;
		try{
			session =  sessionFactory.getCurrentSession(); 
			org.hibernate.Query q= session.createQuery("from UserCommon u where u.uid=? and u.pwd=?"); 
		     q.setString(0, uid);
		     q.setString(1, pwd);
		    user= (UserCommon) q.uniqueResult();
		    if(user!=null)
		    {
					UserCommon userCommon =(UserCommon) session.get(UserCommon.class, user.getId());  //取回对象
					userCommon.setPwd(newPwd);
					session.update(userCommon);
				  return true;
		    }
			else {
				return false;
			}
		}catch(Exception e){e.printStackTrace();return false;}
	}

	@Override
	public UserCommon getUserCommon(long userId) throws Exception {
		
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			UserCommon userCommon =(UserCommon) session.get(UserCommon.class, userId);  //取回对象
            return userCommon;
		}catch(Exception e){e.printStackTrace();return null;}
		
	}

	@Override
	public UserCommon getUser(String uid, String pwdString) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery(" From UserCommon u where u.uid=? and u.pwd=?");
			 q.setString(0, uid);
			 q.setString(1, pwdString);
			 if (q.list().size()>0) {
				 UserCommon userCommon = (UserCommon) q.list().get(0);
				 return userCommon;	
			}else {
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}



	
}
