package com.ws.common;
import it.sauronsoftware.base64.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ws.action.UserAction;
import com.ws.po.UserCommon;
import com.ws.service.UserService;


  
/** 
 * cookie的增加、删除、查询 
 */  
public class CookieUtils {  
    public static final String USER_COOKIE = "user.cookie";  
  
    // 添加一个cookie  
    public Cookie addCookie(UserCommon user) {  
        Cookie cookie = new Cookie(USER_COOKIE, user.getUid() + ","  
                + user.getPwd());  
        System.out.println("添加cookie");  
        cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周  
        return cookie;  
    }  
  
    // 得到cookie  
    public boolean getCookie(HttpServletRequest request, UserService userService) throws Exception {  
        Cookie[] cookies = request.getCookies();  
            System.out.println("cookies: " + cookies); 
        if (cookies != null) {  

            for (Cookie cookie : cookies) {  
                System.out.println("cookie: " + cookie.getName());  
                
                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {  
                    String value = cookie.getValue();  
                    if (StringUtils.isNotBlank(value)) {  
                        String[] split = value.split(",");  
                        String username = split[0];  
                        String password = split[1];  
                        
                        /***************************密码帐号解密*******/
	               	/*	 Map<String, Object> keyMap = RsaUtilsKey.genKeyPair();
						 publicKey = RsaUtilsKey.getPublicKey(keyMap);
						 commonUtil.p(publicKey);
						 byte[] usernameKey =username.getBytes();
						 byte[] passwordKey =password.getBytes();
						   byte[] decodedData = RsaUtilsKey.decryptByPrivateKey(usernameKey, publicKey);
					        String userString = new String(decodedData);
					        byte[] decodedData2 = RsaUtilsKey.decryptByPrivateKey(passwordKey, publicKey);
					        String passString = new String(decodedData2);*/
                        String userString = Base64.decode(username);
                        String passString = Base64.decode(commonUtil.truncateHeadString(password, 3));
                        /*********************************************/
                        System.out.println("自动登录帐号为"+userString+"密码为"+passString);
                        UserCommon user;
						try {
							String ip = ServletActionContext.getRequest().getRemoteAddr();
							user = userService.getUserByPwd(userString, passString);
							  if (user != null) {  
		                            HttpSession session = request.getSession();  
		                            session.setAttribute(UserAction.USER_SESSION, user);// 添加用户到session中  
		                            return true;  
		                        }  
							  else {
								return false;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}  
                      
                    }  
                }  
            }  
        }  
        return false;  
    }  
  
    // 删除cookie  
    public  Cookie delCookie(HttpServletRequest request) {  
        Cookie[] cookies = request.getCookies();  
        if (cookies != null) {  
            for (Cookie cookie : cookies) {  
                if (USER_COOKIE.equals(cookie.getName())) {  
                    cookie.setValue("");  
                    cookie.setMaxAge(0);  
                    commonUtil.p("删除cookie"+cookie.getName());
                    return cookie;  
                }  
            }  
        }  
        return null;  
    }  
} 