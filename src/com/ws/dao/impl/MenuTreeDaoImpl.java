package com.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.dao.MenuTreeDao;
import com.ws.po.MenuTree;

@Component("menuTreeDao")
public class MenuTreeDaoImpl implements MenuTreeDao {

	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuTree> getTree(int rule) throws Exception {
		List<MenuTree> list =null;
		Session session = null;
			session =  sessionFactory.openSession(); 
			org.hibernate.Query q= session.createQuery("from MenuTree  m  where m.authoriy=? and series=1 order by sort asc"); 
			q.setLong(0, rule);
		     list= q.list();
		 	return list;
	}

}
