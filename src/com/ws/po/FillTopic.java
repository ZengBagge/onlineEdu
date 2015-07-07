package com.ws.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;


//题型：填空题
@Entity
public class FillTopic implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @OneToOne(cascade=CascadeType.ALL)
    private Topic topic;
    @OneToMany(mappedBy="fillTopic",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @OrderBy("orderInt asc")
    private List<FillAnswer>answers = new ArrayList<FillAnswer>(); //答案集合

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
	public List<FillAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<FillAnswer> answers) {
		this.answers = answers;
	}


    
    

}
