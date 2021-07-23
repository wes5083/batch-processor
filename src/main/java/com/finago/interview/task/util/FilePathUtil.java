package com.finago.interview.task.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finago.interview.task.constants.Constants;

/**
 * 
 * get the absolute path of PDF and XML file
 */
public class FilePathUtil {

	private static final Logger logger = LoggerFactory.getLogger(FilePathUtil.class);

	/**
	 * absolute path PDF and XML file from in directory
	 * 
	 * @return end separator like c:\xxx\yyy\
	 */
	public static String pathIn() {
		return getProjectAbsolutePath("data" + File.separator + "in" + File.separator);
	}

	/**
	 * absolute path PDF and XML file from archive directory
	 * 
	 * @return end separator like c:\xxx\yyy\
	 */
	public static String pathArchive() {
		return getProjectAbsolutePath("data" + File.separator + "archive" + File.separator);
	}

	/**
	 * absolute path write PDF or XML file to out directory
	 * 
	 * @param subPath
	 * @return root path(end separator) add sub path
	 */
	public static String pathOut(String subPath) {
		return getProjectAbsolutePath("data" + File.separator + "out" + File.separator + subPath);
	}

	/**
	 * absolute path write PDF or XML file to error directory
	 * 
	 * @param subPath
	 * @return root path(end separator) add sub path
	 */
	public static String pathError(String subPath) {
		return getProjectAbsolutePath("data" + File.separator + "error" + File.separator + subPath);
	}

	private static String getProjectAbsolutePath(String subPath) {
		String path = System.getProperty(Constants.PROJECT_ABSOLUTE_PATH);
		if (path == null) {
			path = new File("").getAbsolutePath();
		}
		path = path + File.separator + subPath;
		return path;
	}
}
