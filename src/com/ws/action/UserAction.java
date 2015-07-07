package com.ws.action;

import it.sauronsoftware.base64.Base64;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.CookieUtils;
import com.ws.common.commonUtil;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.service.UserService;
@Component("userAction")
@Scope("prototype")
public class UserAction  extends ActionSupport implements ServletRequestAware,  
ServletResponseAware, SessionAware{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER_SESSION = "user"; 
	 public final static String GOTO_URL_KEY="GOING_TO"; 

	@Resource
	private UserService userService;
	
	private Long id;
    private String uid;
	private String uname;
	private String tname;
	private String pwd;
	private String mail;
	private int rule;
	private boolean[] rember;
	private String errorMesageString;
    private CookieUtils cookieUtils = new CookieUtils(); 
    private HttpServletResponse response;  
    private HttpServletRequest request;
    private StudentUser studentUser;
    private TeacherUser teacherUser;
	private Map<String, Object> session;  
	
	public String  reg() {
		return "form";
	}
	/**
	 * 通过session获取user对象
	 * @return
	 * @throws Exception
	 */

	public String zhuCe() throws Exception{
		
		if(uid !=null &&uid.length()<8)
		{
			setErrorMesageString("学号长度不符合要求");
			return "form";
		}
		else  if(uname!=null && uname.length()<2)
        {
			setErrorMesageString("昵称长度不符合要求");
			return "form";
		}
       else   if(pwd!=null &&pwd.length()<2)
        {
			setErrorMesageString("密码长度不符合要求");
			return "form";
		}
       else {
		
    	   try {
			if(userService.addUser(uid, uname, tname, pwd, rule, mail))
				{
				setErrorMesageString("注册成功");
				return "login";
				}
			else {
				setErrorMesageString("注册发生错误，请检查学号是否正确");
				return"form";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setErrorMesageString("注册发生错误");
			return"form";
		}
	}
	}

public String loginForm() throws Exception {
		
		try {
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			UserCommon userCommon=userService.getUserByPwd(uid, pwd);
			if(userCommon==null || "".equals(userCommon)){
				errorMesageString="学号或密码错误！";
				return "login";
			}
			else{
				commonUtil.p("登录成功");
				userService.setLoginMessage(ip, userCommon);//设置登录信息
				session.put(USER_SESSION, userCommon);   //设置前端session

							/***************************密码帐号加密*******/
							String uidKey = Base64.encode(uid); //帐号加密
                            String  pwdKey =commonUtil.getRandomString(3)+Base64.encode(pwd);	
							/********************************************/
                             Cookie uidCookie=new Cookie("user.cookie",uidKey+","+pwdKey);
                             uidCookie.setMaxAge(604800);//一个星期
							 response.addCookie(uidCookie);// 添加cookie到response中  
				return SUCCESS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMesageString="学号或密码错误！";
			return "login";
		}
		
	}
	public String login() throws Exception{	
		if (cookieUtils.getCookie(request, userService)) {  
            return SUCCESS;  
        } else  
            return "loginform";  
	}
	/**
	 * 用户推出
	 * @return
	 */
	public String logout() {  
        HttpSession session = request.getSession(false);  
        if (session != null)  
            session.removeAttribute(USER_SESSION);  
           session.invalidate();
           Cookie cookie =cookieUtils.delCookie(request);
           if (cookie != null) {
        	   response.addCookie(cookie);
		     }
            commonUtil.p("用户退出登录");
            return "login";  
    }  
	public Boolean changePass(String pwd,String newPwd){
		try {
			UserCommon userCommon=(UserCommon)session.get(USER_SESSION);
			String uid=userCommon.getUid();
			return userService.setPass(pwd,newPwd,uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public String  getStudent() throws Exception {
		UserCommon userCommon=(UserCommon)session.get(USER_SESSION);
		long uid=userCommon.getId();
		studentUser= userService.getStudent(uid);
	 return SUCCESS;
	}
	
	public String getTeacher() throws Exception {
		UserCommon userCommon=(UserCommon)session.get(USER_SESSION);
		long uid=userCommon.getId();
		teacherUser= userService.getTeacher(uid);
		return SUCCESS;
		
	}
	
	public String info(){
		return "info";
	}
	
	public static boolean checkRule(int rule,Object o){
		try {
			UserCommon userCommon =  (UserCommon) o;
			if(userCommon != null){
			int Rule = userCommon.getRule();
			if (Rule == rule) {
				return true;
			}else {
				return false;
			}
			}else {
				return false;
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * 以下为get set 方法
	 * @return
	 */
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}


	public String getErrorMesageString() {
		return errorMesageString;
	}


	public void setErrorMesageString(String errorMesageString) {
		this.errorMesageString = errorMesageString;
	}

	public boolean[] getRember() {
		return rember;
	}

	public void setRember(boolean[] rember) {
		this.rember = rember;
	}

	public CookieUtils getCookieUtils() {
		return cookieUtils;
	}

	public void setCookieUtils(CookieUtils cookieUtils) {
		this.cookieUtils = cookieUtils;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static String getUserSession() {
		return USER_SESSION;
	}
	

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		 this.request=arg0;	
	}
	public StudentUser getStudentUser() {
		return studentUser;
	}
	public void setStudentUser(StudentUser studentUser) {
		this.studentUser = studentUser;
	}
	public TeacherUser getTeacherUser() {
		return teacherUser;
	}
	public void setTeacherUser(TeacherUser teacherUser) {
		this.teacherUser = teacherUser;
	}
   
	
}
