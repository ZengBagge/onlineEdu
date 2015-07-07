package com.ws.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.commonUtil;
import com.ws.po.UserCommon;
import com.ws.service.CollectTopicService;

@Component("collectTopicAction")
@Scope("prototype")
public class CollectTopicAction extends ActionSupport implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object>session;
	@Resource
	private CollectTopicService collectTopicService;
	
	/**
	 * 添加收藏
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public boolean collectTopic(int tid,long uid) throws Exception{
		commonUtil.p("添加收藏");

		if (tid!=0 && tid!=0) {
			try {
				return collectTopicService.addCollect(tid,uid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
		}
	
	public boolean isCollected(int tid,long uid) throws Exception{
		commonUtil.p("检查收藏");

		if (tid!=0 && uid !=0) {
			try {
				return collectTopicService.isCollected(tid,uid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

}
