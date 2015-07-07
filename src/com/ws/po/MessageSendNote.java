package com.ws.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 消息发件箱
 * @author bagge
 *
 */
@Entity
public class MessageSendNote implements Serializable{


	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
	 
	    @OneToOne
	    private Message message;
	    
	    private int del;
	    
	    @OneToOne
	    private UserCommon sender;
        @OneToOne
	    private UserCommon receiver;
	    private Date publishDate;
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

		public int getDel() {
			return del;
		}

		public void setDel(int del) {
			this.del = del;
		}

		public UserCommon getSender() {
			return sender;
		}

		public void setSender(UserCommon sender) {
			this.sender = sender;
		}

		public Date getPublishDate() {
			return publishDate;
		}

		public void setPublishDate(Date publishDate) {
			this.publishDate = publishDate;
		}

		public UserCommon getReceiver() {
			return receiver;
		}

		public void setReceiver(UserCommon receiver) {
			this.receiver = receiver;
		}
	    
	    
}
