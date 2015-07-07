package com.ws.po;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Course implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String titleString;
	@OneToOne
	private TeacherUser teacherUser; //开课老师
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Classes> classes =new HashSet<Classes>(); //开课班级
	
	private String introduce;  //课程介绍
	private int test;  //课程考核形式   0：考试   1：考核     
	private Date addDate;
	
	@OneToMany(mappedBy="course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Topic> topics = new HashSet<Topic>();  //题库
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<QuestionType> questionType=new HashSet<QuestionType>() ;//题型库
	
	@OneToMany(mappedBy="course")
	private List<Work>works =new ArrayList<Work>();
	
	@OneToMany(mappedBy="course")
	private List<WorkTopic> workTopics =new ArrayList<WorkTopic>();
	
	@OneToMany(mappedBy="course")
	private List<Exam>exams =new ArrayList<Exam>();
	
	@OneToMany(mappedBy="course")
	private List<ExamTopic> examTopics =new ArrayList<ExamTopic>();
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
	public TeacherUser getTeacherUser() {
		return teacherUser;
	}
	public void setTeacherUser(TeacherUser teacherUser) {
		this.teacherUser = teacherUser;
	}
	

	public Set<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public Set<QuestionType> getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Set<QuestionType> questionType) {
		this.questionType = questionType;
	}
	public List<Work> getWorks() {
		return works;
	}
	public void setWorks(List<Work> works) {
		this.works = works;
	}
	public List<WorkTopic> getWorkTopics() {
		return workTopics;
	}
	public void setWorkTopics(List<WorkTopic> workTopics) {
		this.workTopics = workTopics;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public List<ExamTopic> getExamTopics() {
		return examTopics;
	}
	public void setExamTopics(List<ExamTopic> examTopics) {
		this.examTopics = examTopics;
	}
	
	
	
	
}
