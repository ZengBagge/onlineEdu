package com.ws.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.HtmlString;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.CourseDao;
import com.ws.dao.DiscuzDao;
import com.ws.dao.TopicDao;
import com.ws.po.Course;
import com.ws.po.DiscuzMessage;
import com.ws.po.Topic;
import com.ws.po.UserCommon;
import com.ws.service.DiscuzService;

@Component("discuzService")
public class DiscuzServiceImpl implements DiscuzService {

	@Resource
	private TopicDao topicDao;
	@Resource
	private DiscuzDao discuzDao;
	@Resource
	private CourseDao courseDao;
	@Override
	public PageBean getInvitation(int i, int page, int tid) throws Exception {
		
		try {
			int allRow=discuzDao.getAllRow(tid);  //获取总行数
			commonUtil.p("获取消息总行数"+allRow);
			int totalPage=PageBean.countTotalPage(i, allRow);  //设置总页数
			final int offset = PageBean.countOffset(i, page);//当前页开始记录
			final int length = i;//每页记录数
			final int currentPage = PageBean.countCurrentPage(page);
			List<DiscuzMessage> discuzMessageList =discuzDao.getList(offset,length,tid);
			PageBean pageBean = new PageBean();
			pageBean.setPageSize(i);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(discuzMessageList);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DiscuzMessage publishInvitation(String body, UserCommon userCommon, long tid)
			throws Exception {
		
				try {
					DiscuzMessage discuzMessage =new DiscuzMessage();
					discuzMessage.setUserCommon(userCommon);
					Topic topic=topicDao.getTopicById(tid);
					if(topic !=null && body.length()>0)
					{
						discuzMessage.setTopic(topic);
						discuzMessage.setPutTime(new Date());
						String bodyString = commonUtil.trimInnerSpaceStr(body); //去掉首尾空格
						discuzMessage.setBody(HtmlString.filterHtml(bodyString));//过滤标签
						if(userCommon.getRule() ==2){
							discuzMessage.setIsTeacher(1);
						}
						return discuzDao.insert(discuzMessage);
					}else {
						return null;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
		
	}

	@Override
	public boolean delInvitation(long aid) throws Exception {
		// TODO Auto-generated method stub
		return discuzDao.delInvitation(aid);
	}

	@Override
	public PageBean getCourseInvitation(int i, int page, int cid)
			throws Exception {
		try {
			int allRow=discuzDao.getAllRowByCourse(cid);  //获取总行数
			commonUtil.p("获取消息总行数"+allRow);
			int totalPage=PageBean.countTotalPage(i, allRow);  //设置总页数
			final int offset = PageBean.countOffset(i, page);//当前页开始记录
			final int length = i;//每页记录数
			final int currentPage = PageBean.countCurrentPage(page);
			List<DiscuzMessage> discuzMessageList =discuzDao.getListByCourse(offset,length,cid);
			PageBean pageBean = new PageBean();
			pageBean.setPageSize(i);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(discuzMessageList);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DiscuzMessage publishCourseInvitation(String body, UserCommon userCommon,
			int cid) throws Exception {
		try {
			DiscuzMessage discuzMessage =new DiscuzMessage();
			discuzMessage.setUserCommon(userCommon);
			Course course = courseDao.getCourseById(cid);
			if(course !=null && body.length()>0)
			{
				discuzMessage.setCourse(course);
				discuzMessage.setPutTime(new Date());
				String bodyString = commonUtil.trimInnerSpaceStr(body); //去掉首尾空格
				discuzMessage.setBody(HtmlString.filterHtml(bodyString));//过滤标签
				if(userCommon.getRule() ==2){
					discuzMessage.setIsTeacher(1);
				}
				return discuzDao.insert(discuzMessage);
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean zan(long id) throws Exception {
		
		try {
			DiscuzMessage discuzMessage = discuzDao.getDiscuzMessage(id);
			discuzMessage.setSort(discuzMessage.getSort()+1);
			return discuzDao.Update(discuzMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
