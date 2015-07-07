package com.ws.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//多选题选项
@Entity
public class CheckAnswer implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	    private String body;
	    
	    private int orderInt=100;
	    @ManyToOne(cascade={CascadeType.ALL})
	    private CheckBoxTopic checkBoxTopic;

	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
	
		public int getOrderInt() {
			return orderInt;
		}
		public void setOrderInt(int orderInt) {
			this.orderInt = orderInt;
		}
		public CheckBoxTopic getCheckBoxTopic() {
			return checkBoxTopic;
		}
		public void setCheckBoxTopic(CheckBoxTopic checkBoxTopic) {
			this.checkBoxTopic = checkBoxTopic;
		}


        
	    
	    

}
