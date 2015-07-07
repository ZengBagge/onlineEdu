package com.ws.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * 题目数据
 * @author bagge
 *
 */
@Entity
public class Topic implements Serializable {


	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
	    
	    private String tid; //编号
	    
	    @ManyToOne
	    private TopicType topicType;
	   
	    private String sourceString;//题目来源
	    @ManyToOne
	    private  QuestionType type;  //题型
	    
	    @ManyToOne
	    private Course course;    //所属课程
	    @ManyToOne
	    private CourseChapter chapter;//所属章节
	    
	    private String titleString; //标题
        
	    private int sum;//练习总次数
	    
	    private int error; //错误次数
	    
	    private int asses; //考核次数
	    private String description;//教师解答
	    
	    private int sort;
	    
	    @ManyToMany(mappedBy="topicList",cascade=CascadeType.ALL)
	    private List<WorkTopic>workTopics =new ArrayList<WorkTopic>();
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public QuestionType getType() {
			return type;
		}

		public void setType(QuestionType type) {
			this.type = type;
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

		public int getSum() {
			return sum;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}

		public int getError() {
			return error;
		}

		public void setError(int error) {
			this.error = error;
		}

		public int getAsses() {
			return asses;
		}

		public void setAsses(int asses) {
			this.asses = asses;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<WorkTopic> getWorkTopics() {
			return workTopics;
		}
		

		public void setWorkTopics(List<WorkTopic> workTopics) {
			this.workTopics = workTopics;
		}

		public int getSort() {
			return sort;
		}

		public void setSort(int sort) {
			this.sort = sort;
		}

		public String getTid() {
			return tid;
		}

		public void setTid(String tid) {
			this.tid = tid;
		}

		public TopicType getTopicType() {
			return topicType;
		}

		public void setTopicType(TopicType topicType) {
			this.topicType = topicType;
		}

		public String getSourceString() {
			return sourceString;
		}

		public void setSourceString(String sourceString) {
			this.sourceString = sourceString;
		}

		public CourseChapter getChapter() {
			return chapter;
		}

		public void setChapter(CourseChapter chapter) {
			this.chapter = chapter;
		}
	

	
	    
	     
}
