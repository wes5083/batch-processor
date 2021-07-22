package com.finago.interview.task.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.finago.interview.task.constants.Constants;
import com.finago.interview.task.entity.Receiver;
import com.finago.interview.task.entity.OutputDataInfo;
import com.finago.interview.task.util.FileOperationUtil;
import com.finago.interview.task.util.FilePathUtil;
import com.finago.interview.task.util.MD5Util;
import com.finago.interview.task.util.XmlOperationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * process XML and PDF file logical
 *
 */
public class BatchProcessorService {

	private static final Logger logger = LoggerFactory.getLogger(BatchProcessorService.class);

	public synchronized void process(File file) {
		// output file data
		List<OutputDataInfo> out = new ArrayList<>();
		List<OutputDataInfo> error = new ArrayList<>();

		String pathArchive = FilePathUtil.pathArchive();
		String pathIn = FilePathUtil.pathIn();

		// 1. read XML file and transfer XML data to output file data
		readXml(file, out, error, pathIn);
		logger.info("read XML file={}, transfer XML to output file data, `out` folder data=[{}], `error` folder data=[{}]", file.getAbsolutePath(), out, error);

		// 2.copy PDF and generate XML file to `out` or `error` folder
		logger.info("copy and generate PDF and XML file to `out` or `error` folder");
		processTargetFile(out);
		processTargetFile(error);

		// 3. move XML file to `archive` folder
		FileOperationUtil.createFolder(pathArchive);
		FileOperationUtil.move(file.getAbsolutePath(), (pathArchive + File.separator + file.getName()));
		logger.info("move XML file={} to archive folder", file.getAbsolutePath());

		// 4. delete all PDF from `in` folder by XML file data
		List<OutputDataInfo> all = new ArrayList<>();
		all.addAll(out);
		all.addAll(error);
		all.forEach(item -> {
			if (item.isExistPdf()) {
				FileOperationUtil.delete(item.getFromFilePath());
				logger.info("delete all PDF from `in` folder, {}", item.getFromFilePath());
			}
		});
	}

	/**
	 * read XML file and transfer XML data to output file data
	 * 
	 * @param file   XML file from `in` folder
	 * @param out    data to `out` folder
	 * @param error  data to `error` folder
	 * @param pathIn XML file `in` folder
	 */
	private void readXml(File file, List<OutputDataInfo> out, List<OutputDataInfo> error, String pathIn) {

		// read XML file
		List<Receiver> list = XmlOperationUtil.readXml(file);
		if (list == null || list.size() == 0) {
			return;
		}
		// transfer XML data to output file data
		list.forEach(item -> {
			String pdfPath = pathIn + item.getFile();

			// PDF file is not exist?
			boolean pdfNotExist = Files.notExists(Paths.get(pdfPath), LinkOption.NOFOLLOW_LINKS);

			// `error` folder( PDF file not exist )
			if (pdfNotExist) {
				processPdfReceiver(item, error, false, false);

				// `out` folder( validate PDF md5 value success )
			} else if (item.getFileMd5().equals(MD5Util.getFileMD5(pdfPath))) {
				processPdfReceiver(item, out, true, true);
				// `error` folder( validate PDF md5 value fail )
			} else {
				processPdfReceiver(item, error, false, true);
			}

		});
	}

	/**
	 * copy PDF and generate XML by output file data
	 * 
	 * @param list copy PDF and generate XML by target file data
	 */
	private void processTargetFile(List<OutputDataInfo> list) {
		list.forEach(item -> {
			boolean existPdf = item.isExistPdf();
			String toPathFolder = item.getToFileFolder();
			String toPathPdf = toPathFolder + File.separator + item.getToFilePdf();

			// create output folder first
			FileOperationUtil.createFolder(toPathFolder);

			// copy PDF
			if (existPdf) {
				FileOperationUtil.copy(item.getFromFilePath(), toPathPdf);
				logger.info("copy PDF file from={}, to={}", item.getFromFilePath(), toPathPdf);
			}

			// generate new XML
			XmlOperationUtil.writeXml(toPathFolder, item.getToFileXml(), item.getXmlData());
			logger.info("generate new XML file={}/{}", toPathFolder, item.getToFileXml());
		});
	}

	/**
	 * transfer XML data to output file data
	 * 
	 * @param receiver XML file data from `in` folder
	 * @param list     output file data
	 * @param success  judge write file to `out` folder(true) or `error` folder(false)
	 * @param existPdf judge PDF file exist?
	 */
	private void processPdfReceiver(Receiver receiver, List<OutputDataInfo> list, boolean success, boolean existPdf) {
		String fileNamePdf = receiver.getFile();
		String inPath = FilePathUtil.pathIn() + fileNamePdf;
		String fileNameXml = fileNamePdf.replaceAll(Constants.FILE_SUFFIX_PDF, Constants.FILE_SUFFIX_XML);

		// The sub folder value is `receiver_id mod 100 / receiver_id`
		int recriveId = receiver.getReceiverId();
		String subFolder = (recriveId % 100) + File.separator + recriveId;

		// output absolute folder
		String outOrErrorFolder = success ? FilePathUtil.pathOut(subFolder) : FilePathUtil.pathError(subFolder);

		list.add(new OutputDataInfo(inPath, outOrErrorFolder, fileNamePdf, fileNameXml, receiver, existPdf));
	}
}
