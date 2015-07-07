package com.ws.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 考试题组
 * @author bagge
 *
 */
@Entity
public class ExamTopic implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    @OneToMany(mappedBy="examTopic")
    private List<Exam> works=new ArrayList<Exam>();  //应用于哪些考试,对方维护
	
    @OneToMany(mappedBy="examTopic")
    private List<ExamQuestionType>  questionTypes=new ArrayList<ExamQuestionType>();  //包含哪些题型(题目数量)，对方维护
    
    @OneToOne
    private TeacherUser teacherUser; //属于哪个老师
    
    @ManyToOne
    private Course course;   //属于哪个课程
    
    
    private String titleString;
    
    private Date addDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Exam> getWorks() {
		return works;
	}

	public void setWorks(List<Exam> works) {
		this.works = works;
	}

	public List<ExamQuestionType> getQuestionTypes() {
		return questionTypes;
	}

	public void setQuestionTypes(List<ExamQuestionType> questionTypes) {
		this.questionTypes = questionTypes;
	}

	public TeacherUser getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(TeacherUser teacherUser) {
		this.teacherUser = teacherUser;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
    
    
    
}
