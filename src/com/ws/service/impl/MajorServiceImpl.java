package com.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ws.dao.MajorDao;
import com.ws.po.Major;
import com.ws.service.MajorService;

@Component("majorService")
public class MajorServiceImpl implements MajorService {

	@Resource
	private MajorDao majorDao;
	@Override
	public List<Major> getAllMajor() throws Exception {
		// TODO Auto-generated method stub
		return majorDao.getMajors();
	}
	public MajorDao getMajorDao() {
		return majorDao;
	}
	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

	
}
