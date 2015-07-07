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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class TeacherUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
	    
	    @ManyToMany(mappedBy="teachers",fetch=FetchType.EAGER)
	    private List<Classes> classes=new ArrayList<Classes>() ;	    //班级
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    private UserCommon userCommon;
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

        
		public List<Classes> getClasses() {
			return classes;
		}

		public void setClasses(List<Classes> classes) {
			this.classes = classes;
		}

		public UserCommon getUserCommon() {
			return userCommon;
		}

		public void setUserCommon(UserCommon userCommon) {
			this.userCommon = userCommon;
		}
	    
	    

}
