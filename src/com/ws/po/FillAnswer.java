package com.ws.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 填空题答案
 * @author bagge
 *
 */
@Entity
public class FillAnswer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	 
    @ManyToOne(cascade={CascadeType.REFRESH},optional=true)//将属性optional设置为true，这可以使得即使外键为空时仍可以向表中添加数据。
    private FillTopic fillTopic;
    
    private String body;
    private int orderInt=100;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public FillTopic getFillTopic() {
		return fillTopic;
	}
	public void setFillTopic(FillTopic fillTopic) {
		this.fillTopic = fillTopic;
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

    
    
}
