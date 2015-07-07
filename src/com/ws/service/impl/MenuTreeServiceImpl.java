package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.ws.dao.MenuTreeDao;
import com.ws.po.MenuTree;
import com.ws.po.UserCommon;
import com.ws.service.MenuTreeService;

@Component("menuTreeService")
public class MenuTreeServiceImpl implements MenuTreeService {

	@Resource
	private MenuTreeDao menuTreeDao;
	@Override
	public List<MenuTree> getTree() throws Exception {
		
		UserCommon userCommon=(UserCommon)ActionContext.getContext().getSession().get("user");
		if(userCommon!=null){
		int rule = userCommon.getRule();
		
		return menuTreeDao.getTree(rule);
		}
		else {
			return null;
		}
	}
	public MenuTreeDao getMenuTreeDao() {
		return menuTreeDao;
	}
	public void setMenuTreeDao(MenuTreeDao menuTreeDao) {
		this.menuTreeDao = menuTreeDao;
	}

	
}
