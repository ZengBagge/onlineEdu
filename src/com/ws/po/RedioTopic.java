package com.ws.po;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//题型：单选题
@Entity
public class RedioTopic implements Serializable{
	
	    private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
	    @OneToOne(cascade=CascadeType.ALL)
	    private Topic topic;
	    private int answer;  //答案：1：A 2：B 3：C 4:D 
	    private String answerA;
	    private String answerB;
	    private String answerC;
	    private String answerD;
	    private String answerE;

		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Topic getTopic() {
			return topic;
		}
		public void setTopic(Topic topic) {
			this.topic = topic;
		}
		public int getAnswer() {
			return answer;
		}
		public void setAnswer(int answer) {
			this.answer = answer;
		}
		public String getAnswerA() {
			return answerA;
		}
		public void setAnswerA(String answerA) {
			this.answerA = answerA;
		}
		public String getAnswerB() {
			return answerB;
		}
		public void setAnswerB(String answerB) {
			this.answerB = answerB;
		}
		public String getAnswerC() {
			return answerC;
		}
		public void setAnswerC(String answerC) {
			this.answerC = answerC;
		}
		public String getAnswerD() {
			return answerD;
		}
		public void setAnswerD(String answerD) {
			this.answerD = answerD;
		}
		public String getAnswerE() {
			return answerE;
		}
		public void setAnswerE(String answerE) {
			this.answerE = answerE;
		}

        
	    
	    
}
