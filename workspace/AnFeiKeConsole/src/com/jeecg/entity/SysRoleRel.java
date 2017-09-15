package com.jeecg.entity;

import com.base.entity.BaseEntity;


public class SysRoleRel extends BaseEntity {
		private String roleId;//   角色主键 sys_role.id	private String objId;//   关联主键 type=0管理sys_menu.id, type=1关联sys_user.id	private String relType;//   关联类型 0=菜单,1=用户
	
	public String getRoleId() {	    return this.roleId;	}	public void setRoleId(String roleId) {	    this.roleId=roleId;	}	public String getObjId() {	    return this.objId;	}	public void setObjId(String objId) {	    this.objId=objId;	}	public String getRelType() {	    return this.relType;	}	public void setRelType(String relType) {	    this.relType=relType;	}
}
