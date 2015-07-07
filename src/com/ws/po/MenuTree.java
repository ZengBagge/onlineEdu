package com.ws.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


/**
 * 菜单数据表
 * @author bagge
 *
 */
@Entity
public class MenuTree implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String titleString;
	private String urlString;
	private int authoriy;//权限  
	private int sort=100;  //排序
	private int series=1;
	@ManyToOne
    private MenuTree father;
	 //子节点
    @OneToMany(mappedBy="father",cascade=CascadeType.ALL)
    @OrderBy("sort asc")
    private Set<MenuTree> chirden =new HashSet<MenuTree>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleString() {
		return titleString;
	}
	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public int getAuthoriy() {
		return authoriy;
	}
	public void setAuthoriy(int authoriy) {
		this.authoriy = authoriy;
	}
	public MenuTree getFather() {
		return father;
	}
	public void setFather(MenuTree father) {
		this.father = father;
	}

	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Set<MenuTree> getChirden() {
		return chirden;
	}
	public void setChirden(Set<MenuTree> chirden) {
		this.chirden = chirden;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	
	
}
