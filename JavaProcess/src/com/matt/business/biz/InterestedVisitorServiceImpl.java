package com.matt.business.biz;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.business.dao.InterestedVisitorDao;
import com.matt.business.model.BDVisitorInfo;
import com.matt.business.model.InterestedVisitor;
import com.matt.common.db.DataSourceKey;


@DataSourceKey(dataSourceKey="srpDataSource")
@Service("interestedVisitorServiceImpl")
public class InterestedVisitorServiceImpl implements InterestedVisitorService {

	@Autowired
	private InterestedVisitorDao interestedVisitorDao;
	
	@Override
	public int addVisitor() {
		for (int i=50001; i<60001; i++)
		{
			InterestedVisitor interestedVisitor = new InterestedVisitor();
			interestedVisitor.setVisitorId("visitor_" + i);
			interestedVisitor.setVisitorName("visitorName_" + i);
			interestedVisitor.setJobId("0582ef55ae");
			interestedVisitor.setLastServiceJobId("0582ef55ae");
			
			Random random = new Random();
			DateTime dateTimeByJdkDate = new DateTime(new Date());
			DateTime markingTime = null;
			if (i%2 == 0) {
				markingTime = dateTimeByJdkDate.plusDays(random.nextInt(365));
			} else {
				markingTime = dateTimeByJdkDate.minusDays(random.nextInt(365));
			}
			interestedVisitor.setMarkingTime(markingTime.toDate());
			interestedVisitor.setSessions(random.nextInt(20));
			
			this.interestedVisitorDao.addInterestedVisitor(interestedVisitor);
			
			int len = random.nextInt(20);
			for (int j=0; j<len; j++)
			{
				BDVisitorInfo visitorInfo = new BDVisitorInfo();
				visitorInfo.setVisitorId("visitor_" + i);
				visitorInfo.setInsuredIdentity((j%4 + 1) + "");
				visitorInfo.setPolicyNo(System.currentTimeMillis() + "");
				if (j%2 == 0)
				{
					visitorInfo.setSplanCode("S20180069");
				} 
				else
				{
					visitorInfo.setSplanCode("S20170249");
				}
				
				int size = random.nextInt(600);
				
				DateTime jodate = new DateTime(new Date());
				DateTime operatorTime = null;
				if (j%3 == 0 || j%3 == 1)
				{
					operatorTime = jodate.minusDays(size);
				}
				else
				{
					operatorTime = jodate.plusDays(size);
				}
				
				visitorInfo.setOperateTime(operatorTime.toDate());
				
				DateTime startTime = operatorTime.plusDays(random.nextInt(100));
				DateTime endTime = startTime.plusYears(1);
				visitorInfo.setStartTime(startTime.toDate());
				visitorInfo.setEndTime(endTime.toDate());
				
				this.interestedVisitorDao.addBDVisitorInfo(visitorInfo);
			}
		}
		
		
		return 0;
	}

	@Override
	public List<InterestedVisitor> selectInterestedVisitor() {
		return this.interestedVisitorDao.selectInterestedVisitor();
	}

}
