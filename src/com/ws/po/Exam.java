package com.ws.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Exam implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private ExamTopic examTopic;
	
	   
    private String titleString;
    
    private Date startDate;
    
    private Date endDate;
    
    private int examTimes; //考试时长，以分钟为单位
    
    
    private String description;//任务说明
    
    @ManyToOne
    private Course course;   //所属课程
    
    @ManyToOne
    private TeacherUser teacherUser;
    
    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Classes>classes =new ArrayList<Classes>();//考试班级
    
    private int zong; //考试对象总人数
    
    private int wan;//考试完成人数

    private Date addDate;
    
    private int isComplate;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExamTopic getExamTopic() {
		return examTopic;
	}

	public void setExamTopic(ExamTopic examTopic) {
		this.examTopic = examTopic;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getExamTimes() {
		return examTimes;
	}

	public void setExamTimes(int examTimes) {
		this.examTimes = examTimes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TeacherUser getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(TeacherUser teacherUser) {
		this.teacherUser = teacherUser;
	}


	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public int getZong() {
		return zong;
	}

	public void setZong(int zong) {
		this.zong = zong;
	}

	public int getWan() {
		return wan;
	}

	public void setWan(int wan) {
		this.wan = wan;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public int getIsComplate() {
		return isComplate;
	}

	public void setIsComplate(int isComplate) {
		this.isComplate = isComplate;
	}
    
    
}
