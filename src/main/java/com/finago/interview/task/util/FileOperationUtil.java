package com.finago.interview.task.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileOperationUtil {

	private static Logger logger = LoggerFactory.getLogger(FileOperationUtil.class);

	/**
	 * create a new directory by absolute path, if the folder exist already, ignore;
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean createFolder(String folder) {
		try {
			Files.createDirectories(Paths.get(folder));
		} catch (Exception e) {
			logger.error("create a new directory={} exception!", folder, e);
			return false;
		}
		return true;
	}

	/**
	 * copy file from one place to another
	 * @param from  complete absolute path
	 * @param to	complete absolute path
	 * @return
	 */
	public static boolean copy(String from, String to) {
		try {
			Path src = Paths.get(from);
			Path dest = Paths.get(to);
			Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.error("copy file from={}, to={} exception", from, to, e);
			return false;
		}
		return true;
	}

	/**
	 * move file from one place to another
	 * @param from  complete absolute path
	 * @param to	complete absolute path
	 * @return
	 */
	public static boolean move(String from, String to) {
		try {
			Path src = Paths.get(from);
			Path dest = Paths.get(to);
			Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.error("move file from={}, to={} exception", from, to, e);
			return false;
		}
		return true;
	}

	/**
	 * delete a file
	 * @param filePath
	 * @return
	 */
	public static boolean delete(String filePath) {
		try {
			Files.deleteIfExists(Paths.get(filePath));
		} catch (IOException e) {
			logger.error("delete a file={} exception", filePath, e);
			return false;
		}
		return true;
	}

	
}