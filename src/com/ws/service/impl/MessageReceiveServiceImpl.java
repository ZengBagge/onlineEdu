package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.MessageReceiveDao;
import com.ws.dao.UserDao;
import com.ws.po.MessageReceiveNote;
import com.ws.po.UserCommon;
import com.ws.service.MessageReceiveService;

@Component("messageReceiveService")
public class MessageReceiveServiceImpl implements MessageReceiveService {

	@Resource
	private MessageReceiveDao messageReceiveDao;
@Resource
private UserDao userDao;
	@Override
	public int getNewReceiveNumber(Long id) throws Exception {
		
		return messageReceiveDao.getNewReceiveNumber(id);
	}

	@Override
	public List<MessageReceiveNote> getReceiveByUser(Long sender, long receiver) throws Exception {
		return  messageReceiveDao.getReceiveBySenderAndReceive(sender,receiver);
	}

	@Override
	public PageBean getReceiveBox(int pageSize, int receivePage, Long id)
			throws Exception {
		try {
			int allRow=messageReceiveDao.getAllRow(id);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, receivePage);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(receivePage);
            List<MessageReceiveNote> messageReceiveNotes =messageReceiveDao.getList(offset,length,id);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(messageReceiveNotes);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public MessageReceiveDao getMessageReceiveDao() {
		return messageReceiveDao;
	}

	public void setMessageReceiveDao(MessageReceiveDao messageReceiveDao) {
		this.messageReceiveDao = messageReceiveDao;
	}

	/**
	 * 更新某人发送的所有消息阅读状态
	 */
	@Override
	public void setReaded(long senderId,long receiverId) throws Exception {
		UserCommon userCommon = userDao.getUserCommon(senderId);
		if (userCommon != null) {
			List<MessageReceiveNote>receiveNotes = messageReceiveDao.getNewReceiveList(senderId,receiverId);
			commonUtil.p("查询到可更新阅读信息的信息"+receiveNotes.size()+"条");;
			if(receiveNotes != null){
			for (MessageReceiveNote m:receiveNotes) {
				m.setReaded(1);
				messageReceiveDao.update(m);
			}
			}
		}
		
	}

	@Override
	public boolean putRubbishBox(long mid) throws Exception {
		return messageReceiveDao.sendRubbish(mid);
	}

	@Override
	public boolean del(long mid) throws Exception {
		return messageReceiveDao.delete(mid);
	}
	

}
