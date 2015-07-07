package com.ws.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 题型数据
 * @author bagge
 *
 */
@Entity
public class QuestionType implements Serializable{  //题型

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String titleString;
    @ManyToMany(mappedBy="questionType",cascade={CascadeType.PERSIST}, fetch=FetchType.EAGER)
	private Set<Course> couses =new HashSet<Course>();
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public Set<Course> getCouses() {
		return couses;
	}

	public void setCouses(Set<Course> couses) {
		this.couses = couses;
	}
	
	
}
