package com.ws.po;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WorkJournal implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    @OneToOne
    private StudentUser studentUser;
    
    @OneToOne
    private Work work;
    
    private int isFinish; //是否完成  0 未完成 1 完成

    private int times; //花费时间
    
    private int completeness; //完成度
    
    private Date adDate;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentUser getStudentUser() {
		return studentUser;
	}

	public void setStudentUser(StudentUser studentUser) {
		this.studentUser = studentUser;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public int getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public Date getAdDate() {
		return adDate;
	}

	public void setAdDate(Date adDate) {
		this.adDate = adDate;
	}

	public int getCompleteness() {
		return completeness;
	}

	public void setCompleteness(int completeness) {
		this.completeness = completeness;
	}

	
    
}
