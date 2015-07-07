package com.ws.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 消息数据表
 * @author bagge
 *
 */
@Entity
public class Message implements Serializable{


	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
	    
	    private String content;//消息内容
	    
	    @OneToMany(mappedBy="message",fetch=FetchType.EAGER)
	    private List<MessageReceiveNote>receivers;
	    @OneToOne
	    private UserCommon sender;
	    
	    private String title;
	    
	    private Date publishTime;

	    private int type;
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public List<MessageReceiveNote> getReceivers() {
			return receivers;
		}

		public void setReceivers(List<MessageReceiveNote> receivers) {
			this.receivers = receivers;
		}

		public UserCommon getSender() {
			return sender;
		}

		public void setSender(UserCommon sender) {
			this.sender = sender;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Date getPublishTime() {
			return publishTime;
		}

		public void setPublishTime(Date publishTime) {
			this.publishTime = publishTime;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		
	    
	    
	    
}
