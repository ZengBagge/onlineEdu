package com.ws.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 消息收件箱
 * @author bagge
 *
 */
@Entity
public class MessageReceiveNote implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
	 
	    @ManyToOne(cascade={CascadeType.ALL})
       private Message message;	   
	    
	    private Date receiveDate;
	    @OneToOne
	    private UserCommon sender;
	    
	    @OneToOne
	    private UserCommon receiver;
	    private int del;
	    
	    private int readed;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Message getMessage() {
			return message;
		}

		public void setMessage(Message message) {
			this.message = message;
		}

		public Date getReceiveDate() {
			return receiveDate;
		}

		public void setReceiveDate(Date receiveDate) {
			this.receiveDate = receiveDate;
		}

	  
		public UserCommon getSender() {
			return sender;
		}

		public void setSender(UserCommon sender) {
			this.sender = sender;
		}

		public int getDel() {
			return del;
		}

		public void setDel(int del) {
			this.del = del;
		}

		public int getReaded() {
			return readed;
		}

		public void setReaded(int readed) {
			this.readed = readed;
		}

		public UserCommon getReceiver() {
			return receiver;
		}

		public void setReceiver(UserCommon receiver) {
			this.receiver = receiver;
		}
	    
	    
}
