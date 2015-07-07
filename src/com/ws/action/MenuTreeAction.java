package com.ws.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.po.MenuTree;
import com.ws.service.MenuTreeService;

@Component("menuTreeAction")
@Scope("prototype")
public class MenuTreeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private MenuTreeService menuTreeService;
	private List<MenuTree> menuList;
	public String getTree() throws Exception {
		
		try {
			menuList= menuTreeService.getTree();
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}
	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}
	public List<MenuTree> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuTree> menuList) {
		this.menuList = menuList;
	}
	
	
}
