package com.ws.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.StudentUserDao;
import com.ws.dao.WorkDao;
import com.ws.dao.WorkJournalDao;
import com.ws.po.StudentUser;
import com.ws.po.Work;
import com.ws.po.WorkJournal;
import com.ws.service.WorkJournalService;

@Component("workJournalService")
public class WorkJournalServiceImpl implements WorkJournalService {

	@Resource
	private WorkJournalDao workJournalDao;
	@Resource
	private StudentUserDao studentUserDao;
	@Resource 
	private WorkDao workDao;
	@Override
	public List<WorkJournal> getWorkJournalsByCourseAndUserCommon(int courseId,
			long userCommonId) throws Exception {
	
		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(userCommonId);
			if (studentUser != null) {
				return workJournalDao.getWorkJournalByCourseAndStudent(courseId, studentUser.getId());
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
	public Boolean addWorkJournal(int workId, long userCommonId, int times,
			int[] result) throws Exception {
		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(userCommonId);
			if (studentUser != null) {
				WorkJournal workJournal =new WorkJournal();
				workJournal.setStudentUser(studentUser);
				Work work = workDao.getWorkById(workId);
				if (work != null) {
					workJournal.setWork(work);
					workJournal.setIsFinish(1);
					workJournal.setAdDate(new Date());
					workJournal.setTimes(times);
					int com =0;
					for (int i = 0; i < result.length; i++) {
						if (result[i] != 0) {
							com ++;
						}
					}
					int completeness=com*100/result.length;
					workJournal.setCompleteness(completeness);
					if( workJournalDao.insert(workJournal))
					{
						workDao.setWan(work);
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
	public Boolean isWorkJournal(int workId, long userCommonId) throws Exception {
		
		try {
			StudentUser studentUser = studentUserDao.getStudentUserByUserCommon(userCommonId);
			if (studentUser != null) {
				return workJournalDao.getIsWorkJournal(workId, studentUser.getId());
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public WorkJournalDao getWorkJournalDao() {
		return workJournalDao;
	}

	public void setWorkJournalDao(WorkJournalDao workJournalDao) {
		this.workJournalDao = workJournalDao;
	}



	public StudentUserDao getStudentUserDao() {
		return studentUserDao;
	}

	public void setStudentUserDao(StudentUserDao studentUserDao) {
		this.studentUserDao = studentUserDao;
	}

	public WorkDao getWorkDao() {
		return workDao;
	}

	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}

	

}
