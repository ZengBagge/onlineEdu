package com.ws.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.po.Classes;
import com.ws.po.Message;
import com.ws.po.MessageReceiveNote;
import com.ws.po.MessageSendNote;
import com.ws.po.StudentUser;
import com.ws.po.TeacherUser;
import com.ws.po.UserCommon;
import com.ws.service.ClassesService;
import com.ws.service.MessageReceiveService;
import com.ws.service.MessageSendService;
import com.ws.service.MessageService;
import com.ws.service.StudentUserService;
import com.ws.service.TeacherUserService;

@Component("messageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
   private StudentUserService studentUserService;
   @Resource
	private TeacherUserService teacherUserService;
   @Resource
   private ClassesService classesService;
   @Resource
   private MessageReceiveService messageReceiveService;
   @Resource
   private MessageSendService messageSendService;
   @Resource
   private MessageService messageService;
	private Map<String, Object>session;
	private Set<StudentUser>studentSet;
	private List<TeacherUser>teachers;
	private List<Classes>classes;
    private PageBean sendBox;
    private PageBean receiveBox;
    private PageBean rubbishBox;
    private int sendPage=1;
    private int receivePage=1;
    private int rubbishPage=1;
	private int moth;
	private int newNumber;
	public String index() throws Exception{
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if (userCommon.getRule() == moth ) {
			sendBox = messageSendService.getSendBox(10,1,userCommon.getId());
			receiveBox =messageReceiveService.getReceiveBox(10,1,userCommon.getId());
			rubbishBox = messageService.getRubbishMessage(10,1,userCommon.getId());
			newNumber= messageReceiveService.getNewReceiveNumber(userCommon.getId());
			commonUtil.p("查询到新消息"+newNumber+"条");
			if(moth==1)
			return "index";
			else if (moth==2) {
				return "indexTeacher";
			}else {
				return ERROR;
			}
		}else {
			return "login";
		}
	}
	
	/**
	 * 获取联系人列表
	 * @throws Exception 
	 */
	public void getLinkmanList() throws Exception{
		try {
			UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
			if (userCommon !=null && userCommon.getRule()==1) {
			     this.studentSet=studentUserService.getMajorUserListByUsercommon(userCommon.getId());	
			     commonUtil.p("查询到联系人用户"+studentSet.size()+"人");
			     this.teachers =teacherUserService.getTeacherListByUsercommon(userCommon.getId());
			     this.classes = classesService.getClassesListByUsercommon(userCommon.getId());
			}else if (userCommon.getRule() ==2) {
				this.studentSet =studentUserService.getMajorUserListByTeacher(userCommon.getId());
				this.teachers=teacherUserService.getGolleague(userCommon.getId());
				this.classes =classesService.getClassesListByTeacher(userCommon.getId());
				commonUtil.p("教师查询到班级共"+classes.size()+"个");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	public void setReaded(long senderId,long receiverId) throws Exception{
		try {
			commonUtil.p("设置已读信息");
			if(senderId !=0 && receiverId !=0)
			messageReceiveService.setReaded(senderId,receiverId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*有未读返回未读，没有未读返回已读前3条*/
	public String[][] getReceiveByUser(long id,long sender) throws Exception{
		
			try {
				List<MessageReceiveNote> meList= messageReceiveService.getReceiveByUser(sender,id);
				String[][] results = new String[meList.size()][2];
				for (int i = 0; i < results.length; i++) {
				  MessageReceiveNote message = meList.get(i);
				  results[i][0]=message.getSender().getPicString();
				  results[i][1]=message.getMessage().getContent();
				}
				setReaded(sender, id);
				return results;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

@SuppressWarnings("unchecked")
public String[][] getMoreReceiveNotes(long uid,int receivePage) throws Exception{

	        try {
				this.receiveBox= messageReceiveService.getReceiveBox(10,receivePage,uid);
				List<MessageReceiveNote>messageReceiveNotes= receiveBox.getList();
				String[][] result = new String[messageReceiveNotes.size()][5];
				commonUtil.p("获取到更多收件箱信息"+messageReceiveNotes.size());
      for (int i = 0; i < result.length; i++) {
				MessageReceiveNote messageReceiveNote=messageReceiveNotes.get(i);
				result[i][0]=messageReceiveNote.getSender().getPicString();
				result[i][1]=messageReceiveNote.getSender().getTname();
				result[i][2]=messageReceiveNote.getSender().getUid();
				result[i][3]=messageReceiveNote.getMessage().getContent();
				result[i][4]=messageReceiveNote.getSender().getId()+"";
           }
      return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

 }
@SuppressWarnings("unchecked")
public String[][] getMoreSendNotes(long uid,int sendPage) throws Exception{

       try {
		    this.sendBox= messageSendService.getSendBox(10,sendPage,uid);
		    List<MessageSendNote>messageSendNotes= sendBox.getList();
			String[][] result = new String[messageSendNotes.size()][5];
			commonUtil.p("获取到更多发件箱信息"+messageSendNotes.size());
		    for (int i = 0; i < result.length; i++) {
					MessageSendNote m=messageSendNotes.get(i);
					result[i][0]=m.getReceiver().getPicString();
					result[i][1]=m.getReceiver().getTname();
					result[i][2]=m.getReceiver().getUid();
					result[i][3]=m.getMessage().getContent();
					result[i][4]=m.getReceiver().getId()+"";
		         }
		    return result;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

}		
@SuppressWarnings("unchecked")
public String[][] getMorePublishMessages(long uid,int rubbishPage) throws Exception{

		this.rubbishBox = messageService.getRubbishMessage(10, rubbishPage, uid);
		List<Message>rubbishMessages=rubbishBox.getList();
		commonUtil.p("获取到更多垃圾信息"+rubbishMessages.size());
		String[][] result = new String[rubbishMessages.size()][5];
	    for (int i = 0; i < result.length; i++) {
				Message m=rubbishMessages.get(i);
				result[i][0]=m.getSender().getPicString();
				result[i][1]=m.getSender().getTname();
				result[i][2]=m.getSender().getUid();
				result[i][3]=m.getContent();
				result[i][4]=m.getSender().getId()+"";
	         }
	    return result;

}

public String[] publishMessage(String content,long[] receivers,HttpSession session) throws Exception{
	UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
	if(userCommon != null && content.length()>0)
	{
		Message message= messageService.addMessage(content,receivers,userCommon,1);
		if(message != null){
		String[] result =new String[2];
		result[0]=message.getSender().getPicString();
		result[1]=message.getContent();
		return result;
		}else {
			return null;
		}
	}else {
		return null;
	}
	
}
	
	
	
	//以下为set get 方法
	
	public int getMoth() {
		return moth;
	}
	public void setMoth(int moth) {
		this.moth = moth;
	}
	public StudentUserService getStudentUserService() {
		return studentUserService;
	}
	public void setStudentUserService(StudentUserService studentUserService) {
		this.studentUserService = studentUserService;
	}
	public TeacherUserService getTeacherUserService() {
		return teacherUserService;
	}
	public void setTeacherUserService(TeacherUserService teacherUserService) {
		this.teacherUserService = teacherUserService;
	}


	public Set<StudentUser> getStudentSet() {
		return studentSet;
	}


	public void setStudentSet(Set<StudentUser> studentSet) {
		this.studentSet = studentSet;
	}
   

	public List<TeacherUser> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherUser> teachers) {
		this.teachers = teachers;
	}

	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public ClassesService getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

	public MessageReceiveService getMessageReceiveService() {
		return messageReceiveService;
	}

	public void setMessageReceiveService(MessageReceiveService messageReceiveService) {
		this.messageReceiveService = messageReceiveService;
	}

	public MessageSendService getMessageSendService() {
		return messageSendService;
	}

	public void setMessageSendService(MessageSendService messageSendService) {
		this.messageSendService = messageSendService;
	}

	public PageBean getSendBox() {
		return sendBox;
	}

	public void setSendBox(PageBean sendBox) {
		this.sendBox = sendBox;
	}

	public PageBean getReceiveBox() {
		return receiveBox;
	}

	public void setReceiveBox(PageBean receiveBox) {
		this.receiveBox = receiveBox;
	}

	public PageBean getRubbishBox() {
		return rubbishBox;
	}

	public void setRubbishBox(PageBean rubbishBox) {
		this.rubbishBox = rubbishBox;
	}

	public int getSendPage() {
		return sendPage;
	}

	public void setSendPage(int sendPage) {
		this.sendPage = sendPage;
	}

	public int getReceivePage() {
		return receivePage;
	}

	public void setReceivePage(int receivePage) {
		this.receivePage = receivePage;
	}

	public int getRubbishPage() {
		return rubbishPage;
	}

	public void setRubbishPage(int rubbishPage) {
		this.rubbishPage = rubbishPage;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public int getNewNumber() {
		return newNumber;
	}

	public void setNewNumber(int newNumber) {
		this.newNumber = newNumber;
	}
	
	

}
