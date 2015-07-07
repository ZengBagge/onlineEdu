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



//题型：多选题
@Entity
public class CheckBoxTopic implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @OneToOne(cascade=CascadeType.ALL)
    private Topic topic;

   private String checkAnswerTrue;
    
    @OneToMany(mappedBy="checkBoxTopic",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @OrderBy("orderInt asc")
    private List<CheckAnswer>answers = new ArrayList<CheckAnswer>();   //选项集合
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
    
	public String getCheckAnswerTrue() {
		return checkAnswerTrue;
	}
	public void setCheckAnswerTrue(String checkAnswerTrue) {
		this.checkAnswerTrue = checkAnswerTrue;
	}
	public List<CheckAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<CheckAnswer> answers) {
		this.answers = answers;
	}

    
    
}
