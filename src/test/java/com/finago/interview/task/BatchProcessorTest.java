package com.finago.interview.task;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finago.interview.task.entity.Receiver;
import com.finago.interview.task.service.BatchProcessorService;
import com.finago.interview.task.util.FileOperationUtil;
import com.finago.interview.task.util.MD5Util;
import com.finago.interview.task.util.XmlOperationUtil;

/**
 * Unit test for simple.
 */
public class BatchProcessorTest {
	
//	private static Logger logger = LoggerFactory.getLogger(BatchProcessorTest.class);
//	
//	@Test
//	public void process() {
//		String xmlFile = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\in\\90072701.xml";
//		BatchProcessorService process = new BatchProcessorService();
//		process.process(new File(xmlFile));
//	}
//	
//	@Test
//	public void md5() {
//		String filePath = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\in\\90072657.pdf";
//		String md5value = MD5Util.getFileMD5(filePath);
//		assertTrue("3ec56d15c9366a5045f5de688867f74a".equals(md5value));
//	}
//	
//	@Test
//	public void readXml() {
//		String filePath = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\in\\90072701.xml";
//		List<Receiver> list= XmlOperationUtil.readXml(new File(filePath));
//		logger.info("read xml result={}", list);
//		assertTrue(list!=null&&list.size()>0);
//	}
//	
//	@Test
//	public void writeXmlToOut() {
//		String filePath = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\out";
//		String fileName = "newfile.xml";
//		Receiver receiver = new Receiver(300000,"first_name_wes","last_name_zhang","file_new_pdf.pdf","file_md5_33333333");
//		boolean result = XmlOperationUtil.writeXml(filePath, fileName, receiver);
//		assert(result == true);
//	}
//	
//	@Test
//	public void copyPdfToOut() {
//		String from = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\in\\90072657.pdf";
//		String to = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\out\\90072657\\90072657.pdf";
//		boolean result = FileOperationUtil.copy(from, to);
//		assert(result == true);
//	}
//	
//	@Test
//	public void movePdfToOut() {
//		String from = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\in\\90072657-2.pdf";
//		String to = "C:\\00_zyw\\02_projects\\eclipse-202106\\batch-processor\\data\\out\\90072657-2\\90072657-2.pdf";
//		boolean result = FileOperationUtil.move(from, to);
//		assert(result == true);
//	}
	
}
