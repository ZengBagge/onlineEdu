package com.ws.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 题目等级：考研题，考博题 等
 * @author bagge
 *
 */
@Entity
public class TopicType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	   
	    private String name;
	    
	    private int sort=100;
	    @OneToOne
	    private TeacherUser teacher;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getSort() {
			return sort;
		}
		public void setSort(int sort) {
			this.sort = sort;
		}
		public TeacherUser getTeacher() {
			return teacher;
		}
		public void setTeacher(TeacherUser teacher) {
			this.teacher = teacher;
		}
	    
	    
}
