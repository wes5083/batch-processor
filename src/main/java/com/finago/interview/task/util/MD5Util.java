package com.finago.interview.task.util;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * get the file's md5 value
 */
public class MD5Util {

	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

	public static String getFileMD5(String filePath) {

		File file = null;
		try {
			file = new File(filePath);
			if (!file.isFile()) {
				return null;
			}
		} catch (Exception e) {
			logger.error("the file={} not exist.", filePath, e);
			return null;
		}

		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();

		} catch (Exception e) {
			logger.error("get the file={} md5 value exception", filePath, e);
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());

		return bigInt.toString(16);
	}

}
