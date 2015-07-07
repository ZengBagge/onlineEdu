package com.ws.po;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//判断题
@Entity
public class JudgeTopic implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
	   
	  @OneToOne
	    private Topic topic;
	  
	    private int answer;  // 0错误  1正确

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

	    
	    

}
