package com.ws.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 收藏的题
 * @author bagge
 *
 */
@Entity
public class CollectTopic implements Serializable{

	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
	 
	    @OneToOne
	    private StudentUser studentUser;
	    
	    @OneToOne
	    private Topic topic;
	    
	    private Date addDate;
        
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public StudentUser getStudentUser() {
			return studentUser;
		}

		public void setStudentUser(StudentUser studentUser) {
			this.studentUser = studentUser;
		}

		public Topic getTopic() {
			return topic;
		}

		public void setTopic(Topic topic) {
			this.topic = topic;
		}

		public Date getAddDate() {
			return addDate;
		}

		public void setAddDate(Date addDate) {
			this.addDate = addDate;
		}
	    
	    
	    
}
