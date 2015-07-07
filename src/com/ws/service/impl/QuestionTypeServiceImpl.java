package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.QuestionTypeDao;
import com.ws.po.QuestionType;
import com.ws.service.QuestionTypeService;

@Component("questionTypeService")
public class QuestionTypeServiceImpl implements QuestionTypeService {

	@Resource
	private QuestionTypeDao questionTypeDao;
	@Override
	public boolean addType(String title) throws Exception {
		// TODO Auto-generated method stub
		QuestionType questionType =new QuestionType();
		if(questionType !=null && title.length()>1)
		   {
			    questionType.setTitleString(title);
                 return questionTypeDao.insertType(questionType);
		   }
		else
		return false;
	}
	public QuestionTypeDao getQuestionTypeDao() {
		return questionTypeDao;
	}
	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}
	@Override
	public List<QuestionType> getTypeList() throws Exception {
		// TODO Auto-generated method stub
		return questionTypeDao.getTypeList();
	}
	@Override
	public List<QuestionType> getTypeListByCourseId(int course) throws Exception {
		// TODO Auto-generated method stub
		return questionTypeDao.getTypeListByCourseId(course);
	}

	
}
