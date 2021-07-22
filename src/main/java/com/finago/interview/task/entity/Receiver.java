package com.finago.interview.task.entity;

import java.io.Serializable;

/**
 * XML data file element entity
 */
public class Receiver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369322752154911196L;

	private int receiverId;

	private String firstName;

	private String lastName;

	private String file;

	private String fileMd5;

	/**
	 * construct method without parameter
	 */
	public Receiver() {
	}

	/**
	 * construct method with parameter
	 */
	public Receiver(int receiverId, String firstName, String lastName, String file, String fileMd5) {
		super();
		this.receiverId = receiverId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.file = file;
		this.fileMd5 = fileMd5;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	@Override
	public String toString() {
		return "Receiver [receiverId=" + receiverId + ", firstName=" + firstName + ", lastName=" + lastName + ", file="
				+ file + ", fileMd5=" + fileMd5 + "]";
	}
}
