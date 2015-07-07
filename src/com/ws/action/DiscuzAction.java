package com.ws.action;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.po.DiscuzMessage;
import com.ws.po.UserCommon;
import com.ws.service.DiscuzService;


@Component("discuzAction")
@Scope("prototype")
public class DiscuzAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tid=0;
	private int cid=0;
	private int page=1;//第几页
    private PageBean pageBean;//包含分布信息的bean
	private Map<String, Object>session;
    @Resource
    private DiscuzService discuzService;
	public String topic() throws Exception{
		commonUtil.p("查找题目ID为"+tid+"的讨论信息");
		if (tid != 0) {
			page=1;
			pageBean=discuzService.getInvitation(8,page,tid);
			return "topic";
		}else {
			return ERROR;
		}	
	}
	public String discuzCourse()throws Exception{
		commonUtil.p("查找课程ID为"+cid+"的讨论信息");
		if (cid != 0) {
			page=1;
			pageBean=discuzService.getCourseInvitation(8,page,cid);
			return "course";
		}else {
			return ERROR;
		}	
		
	}
	
	@SuppressWarnings("unchecked")
	public String[][] moreMessage(int page,int tid,HttpSession session) throws Exception{
		
		try {
			this.pageBean=discuzService.getInvitation(8,page,tid);
			List<DiscuzMessage>list = this.pageBean.getList();
			commonUtil.p("获取到"+list.size()+"条更多信息");
			String[][] resultStrings=new String[list.size()][10]; 
			int i =0;
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon != null){
			for (DiscuzMessage discuzMessage:list) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				String strDate=df.format(discuzMessage.getPutTime());
				resultStrings[i][0]="1";
				resultStrings[i][1]=discuzMessage.getUserCommon().getPicString();
				resultStrings[i][2]=discuzMessage.getUserCommon().getTname();
				resultStrings[i][3]=discuzMessage.getUserCommon().getUname();
				resultStrings[i][4]=discuzMessage.getUserCommon().getLoginIp();
				resultStrings[i][5]=strDate;
				resultStrings[i][6]=discuzMessage.getSort()+"";
				resultStrings[i][7]=discuzMessage.getBody();
				resultStrings[i][8]=discuzMessage.getId()+"";
				resultStrings[i][9]=discuzMessage.getIsTeacher()+"";
				i++;
			}
			}
			return resultStrings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
@SuppressWarnings("unchecked")
public String[][] moreMessageCourse(int page,int cid,HttpSession session) throws Exception{
		
		try {
			this.pageBean=discuzService.getCourseInvitation(8,page,cid);
			List<DiscuzMessage>list = this.pageBean.getList();
			commonUtil.p("获取到"+list.size()+"条更多信息");
			String[][] resultStrings=new String[list.size()][10]; 
			int i =0;
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon != null){
			for (DiscuzMessage discuzMessage:list) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				String strDate=df.format(discuzMessage.getPutTime());
				resultStrings[i][0]="1";
				resultStrings[i][1]=discuzMessage.getUserCommon().getPicString();
				resultStrings[i][2]=discuzMessage.getUserCommon().getTname();
				resultStrings[i][3]=discuzMessage.getUserCommon().getUname();
				resultStrings[i][4]=discuzMessage.getUserCommon().getLoginIp();
				resultStrings[i][5]=strDate;
				resultStrings[i][6]=discuzMessage.getSort()+"";
				resultStrings[i][7]=discuzMessage.getBody();
				resultStrings[i][8]=discuzMessage.getId()+"";
				resultStrings[i][9]=discuzMessage.getIsTeacher()+"";
				i++;
			}
			}
			return resultStrings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] add(String body,int tid,HttpSession session) throws Exception{
		
		String[] resultStrings = new String[9];
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon != null && tid !=0){
				DiscuzMessage discuzMessage=discuzService.publishInvitation(body, userCommon, tid);
				if(discuzMessage !=null)
				{
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					String strDate=df.format(discuzMessage.getPutTime());
					resultStrings[0]="1";
					resultStrings[1]=userCommon.getPicString();
					resultStrings[2]=userCommon.getTname();
					resultStrings[3]=userCommon.getUname();
					resultStrings[4]=userCommon.getLoginIp();
					resultStrings[5]=strDate;
					resultStrings[6]=discuzMessage.getSort()+"";
					resultStrings[7]=discuzMessage.getBody();
					resultStrings[8]=discuzMessage.getId()+"";
				}else {
					resultStrings[0]="0";
				}
			}else {
				resultStrings[0]="0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultStrings[0]="0";
		}
		return resultStrings;
	}
public String[] addCourse(String body,int cid,HttpSession session) throws Exception{
		
		String[] resultStrings = new String[9];
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if(userCommon != null && cid !=0){
				DiscuzMessage discuzMessage=discuzService.publishCourseInvitation(body, userCommon, cid);
				if(discuzMessage !=null)
				{
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					String strDate=df.format(discuzMessage.getPutTime());
					resultStrings[0]="1";
					resultStrings[1]=userCommon.getPicString();
					resultStrings[2]=userCommon.getTname();
					resultStrings[3]=userCommon.getUname();
					resultStrings[4]=userCommon.getLoginIp();
					resultStrings[5]=strDate;
					resultStrings[6]=discuzMessage.getSort()+"";
					resultStrings[7]=discuzMessage.getBody();
					resultStrings[8]=discuzMessage.getId()+"";
				}else {
					resultStrings[0]="0";
				}
			}else {
				resultStrings[0]="0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultStrings[0]="0";
		}
		return resultStrings;
	}
	public boolean zan(long id,HttpSession session)throws Exception{
		
		try {
			String zanSession= (String) session.getAttribute("isZan");
			if (zanSession != null && zanSession.length()>1) {
                     String[] zanString = zanSession.split(",");
				if (!commonUtil.contain(zanString, id+"")) {
					if(discuzService.zan(id))
					 {
						session.setAttribute("isZan", zanSession+","+id);
						return true;
					 }
					else {
						return false;
					}
				}
				else {
					return true;
				}		
			}else {
				if(discuzService.zan(id))
				 {
					session.setAttribute("isZan", zanSession+","+id);
					return true;
				 }
				else {
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		
	}
	
    public boolean del(long aid) throws Exception{
    	return  discuzService.delInvitation(aid);
    }
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public DiscuzService getDiscuzService() {
		return discuzService;
	}
	public void setDiscuzService(DiscuzService discuzService) {
		this.discuzService = discuzService;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	
	
	
}
