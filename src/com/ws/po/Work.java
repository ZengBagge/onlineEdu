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

/**
 * 联系或考试任务数据
 * @author bagge
 *
 */
@Entity
public class Work implements Serializable{

	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	   
	    private String titleString;
	    
	    private Date startDate;
	    
	    private Date endDate;
	    
	    private Date addDate;
	    @ManyToOne
	    private WorkTopic workTopic;
        
	    private int isExam; //是否为考试 0为联系  1为考试
	    
	    private int examTimes; //考试用时
	    
	    private String description;//任务说明
	    
	    @ManyToOne
	    private Course course;   //所属课程
	    @ManyToOne
	    private CourseChapter chapter;//所属章节
	    @ManyToOne
	    private TeacherUser teacherUser;
	    @ManyToMany(fetch=FetchType.EAGER,cascade =CascadeType.ALL)
	    private List<Classes>classes =new ArrayList<Classes>();//练习班级
	    
	    private int zong; //任务对象总人数
	    
	    private int wan;//任务完成人数
	    
	    private int isComplate;  //是否完成，用于前台显示
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

		public WorkTopic getWorkTopic() {
			return workTopic;
		}

		public void setWorkTopic(WorkTopic workTopic) {
			this.workTopic = workTopic;
		}

		public int getIsExam() {
			return isExam;
		}

		public void setIsExam(int isExam) {
			this.isExam = isExam;
		}

		public int getExamTimes() {
			return examTimes;
		}

		public void setExamTimes(int examTimes) {
			this.examTimes = examTimes;
		}


		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getAddDate() {
			return addDate;
		}

		public void setAddDate(Date addDate) {
			this.addDate = addDate;
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

		public int getIsComplate() {
			return isComplate;
		}

		public void setIsComplate(int isComplate) {
			this.isComplate = isComplate;
		}

		public CourseChapter getChapter() {
			return chapter;
		}

		public void setChapter(CourseChapter chapter) {
			this.chapter = chapter;
		}
	    
	    
	    
}
