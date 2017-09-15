package rest.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public class Demo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7470076055624009916L;
	private java.lang.String id;//   主键
	private java.lang.String code;//   编号
	private java.lang.String name;//   名称
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
}
