package com.jeecg.entity;

import com.base.entity.BaseEntity;


public class SysMenuBtn extends BaseEntity {
	
	
	
	private String deleteFlag; //删除标记，与数据库字段无关 Y=删除,其他不删除

		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getId() {
}