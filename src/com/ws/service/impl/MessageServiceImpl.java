package com.ws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.common.HtmlString;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.MessageDao;
import com.ws.dao.MessageReceiveDao;
import com.ws.dao.MessageSendDao;
import com.ws.dao.UserDao;
import com.ws.po.Message;
import com.ws.po.MessageReceiveNote;
import com.ws.po.MessageSendNote;
import com.ws.po.UserCommon;
import com.ws.service.MessageService;

@Component("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageDao messageDao;
	@Resource
	private MessageReceiveDao messageReceiveDao;
	@Resource
	private MessageSendDao messageSendDao;
	@Resource
	private UserDao userDao;
	
	
	@Override
	public PageBean getRubbishMessage(int i, int rubbishPage, Long id)
			throws Exception {
		try {
			int receiveAllRow=messageReceiveDao.getAllRowRubbish(id);  //获取总行数
			int sendAllRow=messageSendDao.getAllRowRubbish(id);  //获取总行数
			int receiveTotalPage=PageBean.countTotalPage(i/2, receiveAllRow);  //设置总页数
			int sendTotalPage=PageBean.countTotalPage(i/2, sendAllRow);  //设置总页数
			final int offset = PageBean.countOffset(i/2, rubbishPage);//当前页开始记录
            final int length = i/2;//每页记录数
            final int currentPage = PageBean.countCurrentPage(rubbishPage);
            
            List<MessageReceiveNote> receiveNotes =messageReceiveDao.getRubbishList(offset,length,id);
            List<MessageSendNote>sendNotes = messageSendDao.getRubbishList(offset,length,id);
            List<Message>messages = new ArrayList<Message>();
            for (MessageReceiveNote m:receiveNotes) {
				messages.add(m.getMessage());
			}
            for (MessageSendNote m:sendNotes) {
				messages.add(m.getMessage());
			}
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(i);
			pageBean.setCurrentPage(currentPage);
			int allRow = receiveAllRow>sendAllRow?receiveAllRow:sendAllRow;
			pageBean.setAllRow(allRow);
			int total = receiveTotalPage>sendTotalPage?receiveTotalPage:sendTotalPage;
			pageBean.setTotalPage(total);
			pageBean.setList(messages);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message addMessage(String content, long[] receivers, UserCommon userCommon,int typeid)
			throws Exception {
		
		Message message =new Message();
		String contentNew=HtmlString.filterHtml(commonUtil.trimInnerSpaceStr(content));//处理字符串
		message.setContent(contentNew);
		if (receivers.length>0) {
			List<UserCommon>userCommons = new ArrayList<UserCommon>();
			for (int i = 0; i < receivers.length; i++) {
				long userId=receivers[i];
				UserCommon receiver= userDao.getUserCommon(userId);
				if(receiver != null){
					userCommons.add(receiver);
				}
			}
		if (userCommons.size()>0) {
			message.setSender(userCommon);
			message.setPublishTime(new Date());
			message.setType(typeid);
			Message message2 = messageDao.insert(message);
			if(message2 != null){
			for(UserCommon u:userCommons){
				MessageReceiveNote messageReceiveNote =new MessageReceiveNote();
				messageReceiveNote.setMessage(message2);
				messageReceiveNote.setReceiveDate(new Date());
				messageReceiveNote.setReceiver(u);
				messageReceiveNote.setSender(userCommon);
				messageReceiveDao.insert(messageReceiveNote);
				 MessageSendNote messageSendNote =new MessageSendNote();
				 messageSendNote.setMessage(message2);
				 messageSendNote.setReceiver(u);
				 messageSendNote.setSender(userCommon);
				 messageSendNote.setPublishDate(new Date());
				 messageSendDao.insert(messageSendNote);
			}
			return message;
		}else {
			return null;
		} 
		}else {
			return null;
		}	
		}else {
			return null;
		}
	}

}































