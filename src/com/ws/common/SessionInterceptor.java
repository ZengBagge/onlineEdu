package com.ws.common;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.ws.action.UserAction;

@Component("sessionInterceptor")
public class SessionInterceptor implements Interceptor{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		  System.out.println("====================拦截器destroy()=========================");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		  System.out.println("====================拦截器 init()=========================");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session=ActionContext.getContext().getSession();   
		  if(session.get(UserAction.USER_SESSION)==null||"".equals(session.get(UserAction.USER_SESSION))){
		   System.out.println("发现没有登陆，返回到登录页面");   
		   return "login";
		  }  
		  else{
			  String result=invocation.invoke(); 
			  System.out.println("====================我定义的拦截器2=========================");  
			  return result;
		  }
	}



	
}
