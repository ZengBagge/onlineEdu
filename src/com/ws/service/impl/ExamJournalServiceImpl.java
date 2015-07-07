package com.ws.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ws.dao.ExamDao;
import com.ws.dao.ExamJournalDao;
import com.ws.dao.StudentUserDao;
import com.ws.po.Exam;
import com.ws.po.ExamJournal;
import com.ws.po.StudentUser;
import com.ws.service.ExamJournalService;

@Component("examJournalService")
public class ExamJournalServiceImpl implements ExamJournalService {

	@Resource
	private StudentUserDao studentUserDao;
	@Resource 
	private ExamDao examDao;
	@Resource
	private ExamJournalDao examJournalDao;
	@Override
	public List<ExamJournal> getExamJournalsByCourseAndUserCommon(int courseId,
			Long id) throws Exception {
		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(id);
			if (studentUser != null) {
				return examJournalDao.getExamJournalByCourseAndStudent(courseId, studentUser.getId());
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
	public Boolean addExamJournal(int ExamId, long userCommonId, int times,
			int[] result) throws Exception {

		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(userCommonId);
			if (studentUser != null) {
				ExamJournal examJournal =new ExamJournal();
				examJournal.setStudentUser(studentUser);
				Exam exam = examDao.getExamById(ExamId);
				if (exam != null) {
					examJournal.setExam(exam);
					examJournal.setIsFinish(1);
					examJournal.setAddDate(new Date());
					examJournal.setTimes(times);
					int com =0;
					for (int i = 0; i < result.length; i++) {
						if (result[i] == 1) {
							com ++;
						}
					}
					int completeness=com*100/result.length;
					examJournal.setMark(completeness);
					if( examJournalDao.insert(examJournal))
					{
						examDao.setWan(exam);
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public Boolean isExamJournal(int ExamId, long userCommonId)
			throws Exception {
		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(userCommonId);
			if (studentUser != null) {
				return examJournalDao.getIsExamJournal(ExamId, studentUser.getId());
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}

	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}

	public ExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}

	public ExamJournalDao getExamJournalDao() {
		return examJournalDao;
	}

	public void setExamJournalDao(ExamJournalDao examJournalDao) {
		this.examJournalDao = examJournalDao;
	}

	
}
