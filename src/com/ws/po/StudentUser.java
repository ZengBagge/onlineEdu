package com.ws.po;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class StudentUser implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
	 @ManyToOne
	   private  Major major;  //专业代码
	 @ManyToOne
	   private Classes classes;    //班级代码
	 @OneToOne(cascade = CascadeType.ALL)
	    private UserCommon userCommon;
	 
	   private long credit;  //积分
       
	   private String autograph; //签名
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public UserCommon getUserCommon() {
		return userCommon;
	}
	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}
	public long getCredit() {
		return credit;
	}
	public void setCredit(long credit) {
		this.credit = credit;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	   
	   
	   
	  

}
