package com.ws.dao;

import com.ws.po.CheckAnswer;

public interface CheckAnswerDao {

	/**
	 * 添加选项
	 * @param checkAnswer
	 * @return
	 * @throws Exception
	 */
	public CheckAnswer insert(CheckAnswer checkAnswer) throws Exception;

}
