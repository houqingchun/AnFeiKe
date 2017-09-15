package biz.entity.people;

import com.base.entity.BaseEntity;
import java.math.BigDecimal;
/**
 * 
 * <br>
 * <b>功能：</b>PeopleEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class People extends BaseEntity {
	
	
	
	private java.lang.String oldPwd;//   旧密码
	private java.lang.String pwdRepeat;//   登陆密码
	
	private java.lang.String remindMe;// 记住我
	
		return remindMe;
	}
	public void setRemindMe(java.lang.String remindMe) {
		this.remindMe = remindMe;
	}
	public java.lang.String getPwdRepeat() {
		return pwdRepeat;
	}
	public void setPwdRepeat(java.lang.String pwdRepeat) {
		this.pwdRepeat = pwdRepeat;
	}
	public java.lang.String getId() {
	public java.lang.String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(java.lang.String oldPwd) {
		this.oldPwd = oldPwd;
	}
}
