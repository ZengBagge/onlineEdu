package com.ws.po;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@SuppressWarnings("serial")
@Entity
public class Classes implements Serializable{
	
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	    private String classUid;  //班级代码
	    private String name;       //班级名称
	    @OneToOne
        private StudentUser monitor;   //班长
        @ManyToOne
       private Major major; //所属专业
        
       @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	    private List<TeacherUser> teachers=new ArrayList<TeacherUser>();
	
   	@ManyToMany(mappedBy="classes",fetch=FetchType.EAGER)
    private Set<Course> courses=new HashSet<Course>();
   	
   public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassUid() {
		return classUid;
	}
	public void setClassUid(String classUid) {
		this.classUid = classUid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StudentUser getMonitor() {
		return monitor;
	}
	public void setMonitor(StudentUser monitor) {
		this.monitor = monitor;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public List<TeacherUser> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<TeacherUser> teachers) {
		this.teachers = teachers;
	}

          
       
}
