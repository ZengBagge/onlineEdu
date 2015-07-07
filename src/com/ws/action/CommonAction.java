package com.ws.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    protected  Map<String, Object>session;
    
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session =arg0;
	}
   
}
