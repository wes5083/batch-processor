package com.finago.interview.task.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.finago.interview.task.constants.Constants;
import com.finago.interview.task.entity.Receiver;

/**
 * 
 * XML file read and write tools
 *
 */
public class XmlOperationUtil {

	private static Logger logger = LoggerFactory.getLogger(XmlOperationUtil.class);

	/**
	 * read XML file data to list
	 * 
	 * @param file
	 * @return
	 */
	public static List<Receiver> readXml(File file) {
		List<Receiver> result = new ArrayList<Receiver>();
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (Exception e) {
			logger.error("read xml file={} is not exist or exception!", file.getName(), e);
			return null;
		}
		// root -> receivers
		Element root = document.getRootElement();

		// element -> receiver
		List<Element> receiverList = root.elements(Constants.XML_RECEIVER);
		for (Element e : receiverList) {
			String receiveId = e.elementTextTrim(Constants.XML_RECEIVER_ID);
			result.add(new Receiver(Integer.parseInt(receiveId), 
					e.elementTextTrim(Constants.XML_FIRST_NAME),
					e.elementTextTrim(Constants.XML_LAST_NAME), 
					e.elementTextTrim(Constants.XML_FILE),
					e.elementTextTrim(Constants.XML_FILE_MD5)));
		}
		return result;
	}

	/**
	 * generate a new XML
	 * 
	 * @param fileAbsoluteFolder file absolute folder
	 * @param fileName           file name
	 * @param xmlData            XML data
	 * @return
	 */
	public static boolean writeXml(String fileAbsoluteFolder, String fileName, Receiver xmlData) {

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(Constants.XML_ROOT);
		Element receiverElement = root.addElement(Constants.XML_RECEIVER);
		receiverElement.addElement(Constants.XML_RECEIVER_ID).setText(String.valueOf(xmlData.getReceiverId()));
		receiverElement.addElement(Constants.XML_FIRST_NAME).setText(xmlData.getFirstName());
		receiverElement.addElement(Constants.XML_LAST_NAME).setText(xmlData.getLastName());
		receiverElement.addElement(Constants.XML_FILE).setText(xmlData.getFile());
		receiverElement.addElement(Constants.XML_FILE_MD5).setText(xmlData.getFileMd5());

		// try-with-resources method close stream
		try (FileWriter fileWriter = new FileWriter(fileAbsoluteFolder + File.separator + fileName)) {

			// format
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(fileWriter, format);
			
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			logger.error("write xml file={}/{} data={} exception!", fileAbsoluteFolder, fileName, xmlData, e);
			return false;
		}

		return true;
	}

}
