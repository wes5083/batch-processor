package com.finago.interview.task;

import com.finago.interview.task.constants.Constants;
import com.finago.interview.task.monitor.FileListenerAdaptor;
import com.finago.interview.task.util.FilePathUtil;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * A simple main method as an example.
 */
public class BatchProcessor {

	private static Logger logger = LoggerFactory.getLogger(BatchProcessor.class);

	public static void main(String[] args) {
		logger.info("*beep boop* ...processing data... *beep boop*");

		// monitor direct
		String rootDir = FilePathUtil.pathIn();

		IOFileFilter directories = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(), HiddenFileFilter.VISIBLE);

		// only monitor XML file
		IOFileFilter files = FileFilterUtils.and(FileFilterUtils.fileFileFilter(),
				FileFilterUtils.suffixFileFilter(Constants.FILE_SUFFIX_XML));
		IOFileFilter filter = FileFilterUtils.or(directories, files);

		// user the filter and set observer
		FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
		observer.addListener(new FileListenerAdaptor());

		// new listener
		FileAlterationMonitor monitor = new FileAlterationMonitor(TimeUnit.SECONDS.toMillis(3), observer);

		// start monitor
		try {
			monitor.start();
		} catch (Exception e) {
			logger.error("monitor file={} start fail!", rootDir, e);
		}

	}

}
