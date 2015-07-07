package com.ws.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ws.common.commonUtil;
import com.ws.dao.FillAnswerDao;
import com.ws.po.FillAnswer;
@Component("fillAnswerDao")
public class FillAnswerDaoImpl implements FillAnswerDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public boolean delete(FillAnswer f) throws Exception {
		
		try {
			Session session =sessionFactory.getCurrentSession();
			commonUtil.p("删除填空题答案");
			session.delete(f);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
