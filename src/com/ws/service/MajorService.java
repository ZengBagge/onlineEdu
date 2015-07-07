package com.ws.service;

import java.util.List;

import com.ws.po.Major;

public interface MajorService {
	
	/**
	 * 获取全部专业
	 * @return
	 * @throws Exception
	 */
	public List<Major> getAllMajor() throws Exception;

}
