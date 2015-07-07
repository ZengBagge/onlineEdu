package com.ws.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

/**
 * 联系或考试任务题库数据
 * @author bagge
 *
 */
@Entity
public class WorkTopic implements Serializable{


	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	 
	    @OneToMany(mappedBy="workTopic")
	    private List<Work> works=new ArrayList<Work>();  //应用于哪些任务,对方维护
	    
	    @ManyToMany( fetch=FetchType.EAGER)
	    private List<QuestionType>  questionTypes=new ArrayList<QuestionType>();  //包含哪些题型，自己维护
	    
	    @ManyToMany( fetch=FetchType.EAGER)
	    @OrderBy("type_id")
	    private List<Topic> topicList =new ArrayList<Topic>();  //题目集合，自己维护
	    
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


		public List<Work> getWorks() {
			return works;
		}

		public void setWorks(List<Work> works) {
			this.works = works;
		}

		public TeacherUser getTeacherUser() {
			return teacherUser;
		}

		public void setTeacherUser(TeacherUser teacherUser) {
			this.teacherUser = teacherUser;
		}

        

		public List<QuestionType> getQuestionTypes() {
			return questionTypes;
		}

		public void setQuestionTypes(List<QuestionType> questionTypes) {
			this.questionTypes = questionTypes;
		}

		public List<Topic> getTopicList() {
			return topicList;
		}

		public void setTopicList(List<Topic> topicList) {
			this.topicList = topicList;
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

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}
	    
	    
}
