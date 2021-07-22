package com.finago.interview.task.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finago.interview.task.service.BatchProcessorService;

/**
 * listener to monitor the XML file create, main business process
 */
public class FileListenerAdaptor extends FileAlterationListenerAdaptor{

    private static final Logger logger = LoggerFactory.getLogger(FileListenerAdaptor.class);

    /**
     * when create a new XML, it will be call.
     */
    @Override
    public void onFileCreate(File file) {
        logger.info("start of process the xml file={}.", file.getAbsolutePath());
        BatchProcessorService process = new BatchProcessorService();
        process.process(file);
        logger.info("end of process the xml file={}.", file.getAbsolutePath());
    }



}