package com.matt.business.dao;

import java.util.List;

import com.matt.business.model.BDVisitorInfo;
import com.matt.business.model.InterestedVisitor;

public interface InterestedVisitorDao {
	
	int addInterestedVisitor(InterestedVisitor bean);
	
	int addBDVisitorInfo(BDVisitorInfo bean);
	
	List<InterestedVisitor> selectInterestedVisitor();

}
