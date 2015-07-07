package com.ws.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;

@Component("heerSSOLoginAction")
@Scope("prototype")
public class HeerSSOLoginAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse  response;
	private Map<String, Object>session;
	@Override
	public String execute() throws Exception {
		commonUtil.p("开始登录");
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("edu.yale.its.tp.cas.client.filter.user");
		commonUtil.p("获取userid"+userid);
				if(userid != null && !"".equals(userid)){
						commonUtil.p("进入成功");
					//根据用户名查找对应本系统对应的用户信息以及加载用户权限
					//登录代码
					//用户加载成功后，需要功能集成时，统一身份认证会传递一个tourl参数，tourl为业务系统的功能地址
					String tourl = request.getParameter("tourl");
					if(tourl!=null && !"".equals(tourl)){
					try {
					response.sendRedirect(request.getContextPath()+"/"+tourl);
					} catch (IOException e) {
					e.printStackTrace();
					}
				}
            }
		return null;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response =arg0;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}


	
}