package com.ws.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.common.PageBean;
import com.ws.dao.MessageSendDao;
import com.ws.po.MessageSendNote;
import com.ws.service.MessageSendService;

@Component("messageSendService")
public class MessageSendServiceImpl implements MessageSendService {

	@Resource
	private MessageSendDao messageSendDao;
	
	@Override
	public PageBean getSendBox(int pageSize, int sendPage, Long id) throws Exception {
		try {
			int allRow=messageSendDao.getAllRow(id);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, sendPage);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(sendPage);
            List<MessageSendNote> messageSendNotes =messageSendDao.getList(offset,length,id);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(messageSendNotes);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
