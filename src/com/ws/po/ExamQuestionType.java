package com.ws.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 考试组卷题型数量表
 * @author bagge
 *
 */
@Entity
public class ExamQuestionType implements Serializable{


	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	    
	 @ManyToOne
	   private ExamTopic examTopic;
	 
	 @OneToOne
	 private QuestionType questionType;
     
	 private int number; //题目数量

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

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	 
	 
}
