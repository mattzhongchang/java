package com.matt.business.biz;

import java.util.List;

import com.matt.business.model.InterestedVisitor;

public interface InterestedVisitorService {

	int addVisitor();
	
	List<InterestedVisitor> selectInterestedVisitor();
}
