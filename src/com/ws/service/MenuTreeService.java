package com.ws.service;

import java.util.List;

import com.ws.po.MenuTree;

public interface MenuTreeService {
	
    /**
     * 根据权限取得菜单
     * @return
     * @throws Exception
     */
	public List<MenuTree>  getTree() throws Exception;
}
