package org.bk.sisample.sequential;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bk.sisample.sequential.SequentialReportGenerator;
import org.bk.sisample.types.Context;
import org.bk.sisample.types.Report;
import org.bk.sisample.types.ReportRequest;
import org.bk.sisample.types.ReportRequestPart;
import org.bk.sisample.types.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"test-primitivethread.xml"})
public class SequentialReportGeneratorTest {
	
	@Resource private SequentialReportGenerator reportGenerator;
	
	private static final Logger logger = LoggerFactory.getLogger(SequentialReportGeneratorTest.class);
	
	@Test
	public void testSequentialReportGeneratorTime(){
		long startTime = System.currentTimeMillis();
		Report report = this.reportGenerator.generateReport(generateReportRequest());
		long timeForReport = System.currentTimeMillis()-startTime;
		
		assertThat(report.getSectionReports().size(), is (5));
		logger.error(String.format("Sequential Report Generator : %s ms", timeForReport));
	}
	
	 
	private ReportRequest generateReportRequest(){
		List<ReportRequestPart> requestParts = new ArrayList<ReportRequestPart>();
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("user","user");
		Context context = new Context(attributes );
	
		ReportRequestPart part1 = new ReportRequestPart(Section.HEADER, context);
		ReportRequestPart part2 = new ReportRequestPart(Section.SECTION1, context);
		ReportRequestPart part3 = new ReportRequestPart(Section.SECTION2, context);
		ReportRequestPart part4 = new ReportRequestPart(Section.SECTION3, context);
		ReportRequestPart part5 = new ReportRequestPart(Section.FOOTER, context);	
		
		requestParts.add(part1);		
		requestParts.add(part2);
		requestParts.add(part3);
		requestParts.add(part4);
		requestParts.add(part5);
		
		ReportRequest reportRequest  = new ReportRequest(requestParts );
		return reportRequest;
	}

}
