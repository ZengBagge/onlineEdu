package com.ws.common.mail;

/**
 * 邮件类
 * @author bagge
 *
 */
public class SimpleMail {

	/**
	 * 主题
	 */
	private String subject;
	
	private Object content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
	
}
