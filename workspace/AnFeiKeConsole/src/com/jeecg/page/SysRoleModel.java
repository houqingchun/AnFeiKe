package com.jeecg.page;

import com.base.page.BasePage;

public class SysRoleModel extends BasePage {
	

		private String id;//   id主键	private String roleName;//   角色名称	private java.sql.Timestamp createTime;//   创建时间	private String createBy;//   创建人	private java.sql.Timestamp updateTime;//   修改时间	private String updateBy;//   修改人	private String state;//   是否可用	private String descr;//   角色描述	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}		public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public java.sql.Timestamp getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.sql.Timestamp createTime) {	    this.createTime=createTime;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public java.sql.Timestamp getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.sql.Timestamp updateTime) {	    this.updateTime=updateTime;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public String getDescr() {	    return this.descr;	}	public void setDescr(String descr) {	    this.descr=descr;	}
	
}
