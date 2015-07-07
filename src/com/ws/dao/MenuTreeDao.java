package com.ws.dao;

import java.util.List;

import com.ws.po.MenuTree;

public interface MenuTreeDao {

	public List<MenuTree> getTree(int rule) throws Exception;
}
