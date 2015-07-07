package com.ws.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserCommon implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
	
	    private String uid;
	
	    private String uname;
		
	    private String tname;
		
	    private String pwd;
		
	    private String loginIp;
		
	    private Date addTime;
		
	    private Date loginTime;
		
	    private String mail;
		
	    private int rule;            //1学生  2教师  3管理员      
        
	    private String picString="avatar.jpg";
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
		public String getLoginIp() {
			return loginIp;
		}
		public void setLoginIp(String loginIp) {
			this.loginIp = loginIp;
		}
		public Date getAddTime() {
			return addTime;
		}
		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}
		public Date getLoginTime() {
			return loginTime;
		}
		public void setLoginTime(Date loginTime) {
			this.loginTime = loginTime;
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
		public String getPicString() {
			return picString;
		}
		public void setPicString(String picString) {
			this.picString = picString;
		}

		
		
		

}
