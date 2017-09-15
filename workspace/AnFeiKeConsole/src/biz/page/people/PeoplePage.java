package biz.page.people;

import com.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>PeoplePage<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class PeoplePage extends BasePage {
	

		private java.lang.String id;//   id主键	private java.lang.String account;//   登陆帐号	private java.lang.String email;//   邮箱	private java.lang.String pwd;//   登陆密码	private java.lang.String name;//   昵称	private java.lang.String mobile;//   移动电话	private java.lang.String type;//   用户类别	private java.lang.String isAvailable;//   是否可用	private java.lang.Integer loginCount;//   登录总次数	private java.util.Date loginTime;//   最后登录时间	private java.lang.String isDeleted;//   是否删除	private java.util.Date createTime;//   创建时间	private java.util.Date updateTime;//   修改时间	private java.lang.String createBy;//   创建人	private java.lang.String updateBy;//   修改人	private java.lang.String isAdmin;//   是否为管理员
	
	private java.lang.String remindMe;// 记住我
		public java.lang.String getRemindMe() {
		return remindMe;
	}
	public void setRemindMe(java.lang.String remindMe) {
		this.remindMe = remindMe;
	}
	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getAccount() {	    return this.account;	}	public void setAccount(java.lang.String account) {	    this.account=account;	}	public java.lang.String getEmail() {	    return this.email;	}	public void setEmail(java.lang.String email) {	    this.email=email;	}	public java.lang.String getPwd() {	    return this.pwd;	}	public void setPwd(java.lang.String pwd) {	    this.pwd=pwd;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.String getMobile() {	    return this.mobile;	}	public void setMobile(java.lang.String mobile) {	    this.mobile=mobile;	}	public java.lang.String getType() {	    return this.type;	}	public void setType(java.lang.String type) {	    this.type=type;	}	public java.lang.String getIsAvailable() {	    return this.isAvailable;	}	public void setIsAvailable(java.lang.String isAvailable) {	    this.isAvailable=isAvailable;	}	public java.lang.Integer getLoginCount() {	    return this.loginCount;	}	public void setLoginCount(java.lang.Integer loginCount) {	    this.loginCount=loginCount;	}	public java.util.Date getLoginTime() {	    return this.loginTime;	}	public void setLoginTime(java.util.Date loginTime) {	    this.loginTime=loginTime;	}	public java.lang.String getIsDeleted() {	    return this.isDeleted;	}	public void setIsDeleted(java.lang.String isDeleted) {	    this.isDeleted=isDeleted;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}	public java.lang.String getIsAdmin() {	    return this.isAdmin;	}	public void setIsAdmin(java.lang.String isAdmin) {	    this.isAdmin=isAdmin;	}
	
}
