package com.jeecg.entity;

import com.base.entity.BaseEntity;


public class SysUser extends BaseEntity {
	
	
	
	private String superAdmin;//超级管理员
	
	private String roleStr;//用户权限, 按","区分
	
	
	
		return roleStr;
	}
	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
	public String getId() {
	public String getSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}
}