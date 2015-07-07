package com.ws.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 记录用户作题记录
 * @author bagge
 *
 */
@Entity
public class TopicCheckNotes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Topic topic;
	
	@ManyToOne
	private UserCommon userCommon;
	
	private Date addDate;
	@ManyToOne
	private Work work;
	
	private int state; //是否正确 正确为1,错误为0
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public UserCommon getUserCommon() {
		return userCommon;
	}
	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
