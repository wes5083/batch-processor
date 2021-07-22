package com.finago.interview.task.entity;

import java.io.Serializable;

/**
 * organize data before write to `out` or `error` folder
 *
 */
public class OutputDataInfo implements Serializable {
	/**
	 */
	private static final long serialVersionUID = -6411001424820537483L;

	/** `in` folder file path absolute path */
	private String fromFilePath;

	/** `out` or `error` folder absolute folder */
	private String toFileFolder;

	/** `out` or `error` PDF file name */
	private String toFilePdf;

	/** `out` or `error` XML file name */
	private String toFileXml;

	/** `in` folder XML file data */
	private Receiver xmlData;

	/** this record not exist pdf file in folder `in` */
	private boolean existPdf;

	/**
	 * construct method without parameter
	 */
	public OutputDataInfo() {
	}

	/**
	 * construct method with parameter
	 */
	public OutputDataInfo(String fromFilePath, String toFileFolder, String toFilePdf, String toFileXml,
			Receiver xmlData, boolean existPdf) {
		super();
		this.fromFilePath = fromFilePath;
		this.toFileFolder = toFileFolder;
		this.toFilePdf = toFilePdf;
		this.toFileXml = toFileXml;
		this.xmlData = xmlData;
		this.existPdf = existPdf;
	}

	public String getFromFilePath() {
		return fromFilePath;
	}

	public void setFromFilePath(String fromFilePath) {
		this.fromFilePath = fromFilePath;
	}

	public String getToFileFolder() {
		return toFileFolder;
	}

	public void setToFileFolder(String toFileFolder) {
		this.toFileFolder = toFileFolder;
	}

	public String getToFilePdf() {
		return toFilePdf;
	}

	public void setToFilePdf(String toFilePdf) {
		this.toFilePdf = toFilePdf;
	}

	public String getToFileXml() {
		return toFileXml;
	}

	public void setToFileXml(String toFileXml) {
		this.toFileXml = toFileXml;
	}

	public Receiver getXmlData() {
		return xmlData;
	}

	public void setXmlData(Receiver xmlData) {
		this.xmlData = xmlData;
	}

	public boolean isExistPdf() {
		return existPdf;
	}

	public void setExistPdf(boolean existPdf) {
		this.existPdf = existPdf;
	}


}
