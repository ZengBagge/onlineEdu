package com.ws.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 博文类型
 */
@Entity
public class BlogType implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    private String typename;
    private int sortrank=100;
    private int grade;
    @ManyToOne
   private BlogType father;
   @OneToOne
    private UserCommon userCommon;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getSortrank() {
		return sortrank;
	}

	public void setSortrank(int sortrank) {
		this.sortrank = sortrank;
	}

	public BlogType getFather() {
		return father;
	}

	public void setFather(BlogType father) {
		this.father = father;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	} 
    
    
    
}
