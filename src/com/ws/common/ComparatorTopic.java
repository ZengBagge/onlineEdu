package com.ws.common;

import java.util.Comparator;

import com.ws.po.Topic;

public class ComparatorTopic implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		
		Topic topic =(Topic)  o1;
		Topic topic2 = (Topic) o2;
		Integer id1 = topic.getType().getId();
		Integer id2= topic2.getType().getId();
		int flag =id1.compareTo(id2);
		return flag;
	}

}
