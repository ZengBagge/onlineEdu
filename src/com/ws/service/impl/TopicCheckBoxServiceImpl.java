package com.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ws.common.PageBean;
import com.ws.common.commonUtil;
import com.ws.dao.CheckAnswerDao;
import com.ws.dao.CourseChapterDao;
import com.ws.dao.CourseDao;
import com.ws.dao.QuestionTypeDao;
import com.ws.dao.TopicCheckBoxDao;
import com.ws.dao.TopicDao;
import com.ws.dao.TopicTypeDao;
import com.ws.po.CheckAnswer;
import com.ws.po.CheckBoxTopic;
import com.ws.po.Course;
import com.ws.po.CourseChapter;
import com.ws.po.QuestionType;
import com.ws.po.Topic;
import com.ws.po.TopicType;
import com.ws.service.TopicCheckBoxService;

@Component("topicCheckBoxService")
public class TopicCheckBoxServiceImpl implements TopicCheckBoxService {

	@Resource
	private TopicDao topicDao;
	@Resource
	private TopicCheckBoxDao topicCheckBoxDao;
	@Resource
	private CourseDao courseDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private CheckAnswerDao checkAnswerDao;
	@Resource
	private CourseChapterDao courseChapterDao;
	@Resource
	private TopicTypeDao topicTypeDao;
	@Override
	public PageBean getList(int pageSize, int page, int courseId)
			throws Exception {
		try {
			int allRow=topicCheckBoxDao.getAllRow(courseId);  //获取总行数
			System.out.print("总行数为："+allRow);
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			System.out.print("总页数为："+totalPage);
			System.out.println();
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<CheckBoxTopic> checkTopicList =topicCheckBoxDao.getList(offset,length,courseId);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(checkTopicList);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addTopic(String title, String[] answerTrue, String[] answer,
			String description, int courseId, int i,int topicType,String topicSource,int courseChapter,String tid) throws Exception {
				
		         if(answerTrue.length<1 || answer.length<1)
			              return false;
		         System.out.print("答案个数："+answerTrue.length +"选项个数" +answer.length +"课程ID为："+courseId +"题型为："+i);
		        Topic topic =new Topic();
				CheckBoxTopic checkBoxTopic= new CheckBoxTopic();
		         topic.setTitleString(title);
		         topic.setDescription(description);
		 		topic.setSourceString(topicSource);
		 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
		 	    	topic.setTid(tid);//设置自编编号
		 		else {
					return false;
				}
				CourseChapter chapter=courseChapterDao.getChapter(courseChapter);
				if(chapter == null)
					return false;
				topic.setChapter(chapter);
				TopicType topicType2=topicTypeDao.getTopicType(topicType);
				if (topicType2 == null) {
					return false;
				}
				topic.setTopicType(topicType2);
		         try {
					Course course =courseDao.getCourseById(courseId);  //获取课程对象
					 QuestionType questionType=questionTypeDao.getQuestionTypeById(i); //获取题型
					 if(course != null && questionType != null)
					 {
						 try {
							topic.setCourse(course);
							 topic.setType(questionType);
							 Topic topic2= topicDao.insetTopic(topic);
							 if(topic2 != null)
							 {
								 checkBoxTopic.setTopic(topic2);
								 List<CheckAnswer>checkAnswers=new ArrayList<CheckAnswer>();
								
						         for (int j = 0; j < answer.length; j++) {
						        	   CheckAnswer checkAnswer =new CheckAnswer();
										String bodyString=answer[j];
										checkAnswer.setBody(bodyString);
										checkAnswer.setOrderInt(j);
										checkAnswer.setCheckBoxTopic(checkBoxTopic);
										   String jString =String.valueOf(j);
												   System.out.print("转换后的数据为："+jString);
										checkAnswers.add(checkAnswer);
									}

						         checkBoxTopic.setAnswers(checkAnswers);
                                 String checkAnswerTrueString= StringUtils.join( answerTrue, ",");
                                 checkBoxTopic.setCheckAnswerTrue(checkAnswerTrueString);
						         try {									 
									return topicCheckBoxDao.insert(checkBoxTopic);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									return false;
								}
							 }
							 else {
								return false;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}
					 }
					 else {
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

	}
	@Override
	public Boolean batchImport(String[][] result, String courseId)
			throws Exception {

		try {
			boolean re[] =new boolean[result.length];
			String answer[] =new String[result[0].length-3];
			if(courseId.length()>0)
			{
				int courseIdInt=Integer.parseInt(courseId);
				for (int i = 0; i < result.length; i++) {
					if(result[i][0].equals(""))
						continue;
					String tid=result[i][0];
					String title=result[i][1];
					String topicTypeString=result[i][2];
					int topicType =Integer.parseInt(topicTypeString);
					String chapterString =result[i][3];
					int chapter =Integer.parseInt(chapterString);
					String[] answerTrue=result[i][4].split(",");
					System.out.print("导入答案个数为"+answerTrue.length);
					for (int j = 0; j < answerTrue.length; j++) {
						System.out.println();
						System.out.print(answerTrue[j]);
					}
					String[] anStrings=new String[answerTrue.length];
					for (int j = 0; j < answerTrue.length; j++) {
						if(answerTrue[j].equals("A") || answerTrue[j].equals("a"))
							anStrings[j]="0";
						else if(answerTrue[j].equals("B") || answerTrue[j].equals("b")) //不能使用String == String  的形式判断
							anStrings[j]="1";
						else if(answerTrue[j].equals("C") || answerTrue[j].equals("c"))
							anStrings[j]="2";
						else if(answerTrue[j].equals("D") || answerTrue[j].equals("d"))
							anStrings[j]="3";
						else if(answerTrue[j].equals("E") || answerTrue[j].equals("e"))
							anStrings[j]="4";
						else if(answerTrue[j].equals("F")|| answerTrue[j].equals("f"))
							anStrings[j]="5";
						else {
							return false;
						}
					}
					for (int j = 0; j < anStrings.length; j++) {
						System.out.print(anStrings[j]);
						System.out.println();
					}
					String description =result[i][5];
					String topicSource=result[i][6];
					int k=0;
				    for (int j = 7; j < result[i].length; j++) {
						answer[k]=result[i][j];
						k++;
					}
						if(title.length()>1)
						{
							re[i]=this.addTopic(title, answerTrue, answerTrue, description, courseIdInt, 2, topicType, topicSource, chapter, tid);
						}
					
				}
				for (int i = 0; i < re.length; i++) {
					if(!re[i])
						return false;
				}
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}


	@Override
	public boolean checkAnswer(String[] answerInt, int tidInt) throws Exception {
	
		CheckBoxTopic checkBoxTopic = topicCheckBoxDao.getCheckBoxById(tidInt);
		String answerTrue = checkBoxTopic.getCheckAnswerTrue();
		long topicId= checkBoxTopic.getTopic().getId();
		String answerString =StringUtils.join( answerInt, ",");
		commonUtil.p("多选题答案检查，标准答案"+answerTrue+"用户答案"+answerString);
		topicDao.addsum(topicId);
		if (answerTrue.equals(answerString)) {
			return true;
		}else {
			topicDao.addError(topicId);
			return false;
		}
	}
	
	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public TopicCheckBoxDao getTopicCheckBoxDao() {
		return topicCheckBoxDao;
	}

	public void setTopicCheckBoxDao(TopicCheckBoxDao topicCheckBoxDao) {
		this.topicCheckBoxDao = topicCheckBoxDao;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public QuestionTypeDao getQuestionTypeDao() {
		return questionTypeDao;
	}

	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}


	public CheckAnswerDao getCheckAnswerDao() {
		return checkAnswerDao;
	}

	public void setCheckAnswerDao(CheckAnswerDao checkAnswerDao) {
		this.checkAnswerDao = checkAnswerDao;
	}

	@Override
	public boolean update(long id, String title, String[] answerTrue,
			String[] answer, String decription, int i,int topicType,String topicSource,int sourceChapter,String tid)
			throws Exception {
		   if(answerTrue.length<1 || answer.length<1)
	              return false;
      System.out.print("答案个数："+answerTrue.length +"选项个数" +answer.length +"题型为："+i);

		CheckBoxTopic checkBoxTopic= topicCheckBoxDao.getCheckBoxById(id);
	     Topic topic =checkBoxTopic.getTopic();
                   topic.setTitleString(title);
                  topic.setDescription(decription);
  		 		topic.setSourceString(topicSource);
		 		if(tid.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) //判断是否符合格式
		 	    	topic.setTid(tid);//设置自编编号
		 		else {
					return false;
				}
  				CourseChapter chapter=courseChapterDao.getChapter(sourceChapter);
  				if(chapter == null)
  					return false;
  				topic.setChapter(chapter);
  				TopicType topicType2=topicTypeDao.getTopicType(topicType);
  				if (topicType2 == null) {
  					return false;
  				}
  				topic.setTopicType(topicType2);
      try {
			 QuestionType questionType=questionTypeDao.getQuestionTypeById(i); //获取题型
			 if( questionType != null)
			 {
					 topic.setType(questionType);
						 checkBoxTopic.setTopic(topic);
						 List<CheckAnswer>checkAnswers=checkBoxTopic.getAnswers();
							commonUtil.removeDuplicate(checkAnswers);
				         for (int j = 0; j < answer.length; j++) {
				        	 for(int m=0;m<checkAnswers.size();m++)
				        	   {
				        	   CheckAnswer checkAnswer =checkAnswers.get(m);
				        	   if (checkAnswer.getOrderInt() == j) {
				        			String bodyString=answer[j];
									commonUtil.p("修改前"+checkAnswer.getBody());
									checkAnswer.setBody(bodyString);
									commonUtil.p("修改后"+checkAnswer.getBody());
							   }
				        	 }
				        }

				         checkBoxTopic.setAnswers(checkAnswers);
                      String checkAnswerTrueString= StringUtils.join( answerTrue, ",");
                      checkBoxTopic.setCheckAnswerTrue(checkAnswerTrueString);
				         try {									 
							return topicCheckBoxDao.insert(checkBoxTopic);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}
					 }
					 else {
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			 }


	@Override
	public boolean delete(long tid) throws Exception {
		// TODO Auto-generated method stub
		return topicCheckBoxDao.delete(tid);
	}



	@Override
	public CheckBoxTopic getCheckBox(long checkBoxTopicId) throws Exception {
		// TODO Auto-generated method stub
		return topicCheckBoxDao.getCheckBoxById(checkBoxTopicId);
	}


 



	
}
