package rest.entity;

import java.io.Serializable;

public class WsCallResult  implements Serializable {
	public static final String UPLOAD_SUCCESS = "Upload successfuly.";
	public static final String UPLOAD_FILE_EMPTY = "There's no files to be uploaded.";
	public static final String LOGIN_SUCCESS = "Login successfuly.";
	public static final String LOGIN_ERROR = "Invalid username or password.";
	public static final String INVALID_INSPECTION_ID = "Invalid inspection id.";
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6407442181443897275L;

	private Object data;
	private String code;//返回编码，以表示成功或失败，或失败原则
	private String message;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
