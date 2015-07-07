package com.ws.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * 博文
 * @author bagge
 *
 */
@Entity
public class Blog implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    
    private String titleString;
    
    @ManyToOne
    private UserCommon userCommon;
    
    private Date publishDate;
    
    private String shortMessage;
    
    private String contentString;
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<BlogTag>tags=new HashSet<BlogTag>();
    
    private String userIpString;
    @ManyToOne
    private BlogType blogType;
    
    private String writer;

    private String description;
    
    private String keywords;
    
    private int isIndex;//是否首页显示
    
    private long host; //热度
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public Set<BlogTag> getTags() {
		return tags;
	}

	public void setTags(Set<BlogTag> tags) {
		this.tags = tags;
	}

	public String getUserIpString() {
		return userIpString;
	}

	public void setUserIpString(String userIpString) {
		this.userIpString = userIpString;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	public long getHost() {
		return host;
	}

	public void setHost(long host) {
		this.host = host;
	}
    
   
}
