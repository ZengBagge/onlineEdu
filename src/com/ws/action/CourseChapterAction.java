package com.ws.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;
import com.ws.po.CourseChapter;
import com.ws.service.CourseChapterService;

@Component("courseChapterAction")
@Scope("prototype")
public class CourseChapterAction extends ActionSupport implements SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Map<String, Object>session;
    private List<CourseChapter>chapters;
    private int courseId;
    @Resource
    private CourseChapterService courseChapterService;
    
    public void getChapterList() throws Exception{
        int 	 courseId =  (int) ServletActionContext.getRequest().getAttribute("courseId"); //必须添加属性set get 方法
    	this.chapters = courseChapterService.getChapters(courseId);
    }
    
    public boolean addChapter(String titleString,int sort,String shortMessage,int courseId)throws Exception{
    	
    	try {
			if(!titleString.equals("")&&sort !=0){
				commonUtil.p("标题"+titleString);
			return 	courseChapterService.add(titleString,shortMessage,sort,courseId);
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    }
    
    
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session =arg0;
	}

	public List<CourseChapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<CourseChapter> chapters) {
		this.chapters = chapters;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public CourseChapterService getCourseChapterService() {
		return courseChapterService;
	}

	public void setCourseChapterService(CourseChapterService courseChapterService) {
		this.courseChapterService = courseChapterService;
	}

	
}
