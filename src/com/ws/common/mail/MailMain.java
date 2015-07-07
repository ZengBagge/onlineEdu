package com.ws.common.mail;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import com.ws.common.commonUtil;

/**
 * 多线程发送邮件
 * @author bagge
 *
 */
public class MailMain extends Thread{

	/**
	 * 发送邮件信息容器
	 */
    private Map<String, SimpleMail>sendInfo;
    /**
     * 发送者
     */
	private String sender="576501057@qq.com";
	private String pwd="576501057";
	
	/**
	 * 构造方法
	 * @param sendInfo
	 */
	public MailMain(Map<String,SimpleMail> sendInfo){
          this.sendInfo=sendInfo;
	}

	public  void run() {
		try {
			for (Map.Entry<String, SimpleMail> m:sendInfo.entrySet()) {
            String receiver = m.getKey();
            SimpleMail simpleMail=m.getValue();
            commonUtil.p("后台发送邮件给"+receiver);
            MailSender mailSender = new MailSender(sender, pwd);
			mailSender.send(receiver, simpleMail);
			sleep(1000);  //1000毫秒发送一封
		  }
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public Map<String, SimpleMail> getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(Map<String, SimpleMail> sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
    
	
}
