package biz.entity.recruit;

import com.base.entity.BaseEntity;
import java.math.BigDecimal;
/**
 * 
 * <br>
 * <b>功能：</b>RecruitEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class Recruit extends BaseEntity {
	
		private java.lang.String id;//   主键	private java.lang.String jobTitle;//   职位名称	private java.lang.String jobBase;//   工作地	private java.lang.Integer headCount;//   需要人数	private java.lang.Object jobReq;//   职位要求	private java.util.Date openDate;//   开放时间	private java.lang.String contact;//   联系人	private java.lang.String tel;//   座机	private java.lang.String mobile;//   手机	private java.lang.String createBy;//   创建人	private java.util.Date createTime;//   创建时间	private java.lang.String updateBy;//   更新人	private java.util.Date updateTime;//   修改时间	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getJobTitle() {	    return this.jobTitle;	}	public void setJobTitle(java.lang.String jobTitle) {	    this.jobTitle=jobTitle;	}	public java.lang.String getJobBase() {	    return this.jobBase;	}	public void setJobBase(java.lang.String jobBase) {	    this.jobBase=jobBase;	}	public java.lang.Integer getHeadCount() {	    return this.headCount;	}	public void setHeadCount(java.lang.Integer headCount) {	    this.headCount=headCount;	}	public java.lang.Object getJobReq() {	    return this.jobReq;	}	public void setJobReq(java.lang.Object jobReq) {	    this.jobReq=jobReq;	}	public java.util.Date getOpenDate() {	    return this.openDate;	}	public void setOpenDate(java.util.Date openDate) {	    this.openDate=openDate;	}	public java.lang.String getContact() {	    return this.contact;	}	public void setContact(java.lang.String contact) {	    this.contact=contact;	}	public java.lang.String getTel() {	    return this.tel;	}	public void setTel(java.lang.String tel) {	    this.tel=tel;	}	public java.lang.String getMobile() {	    return this.mobile;	}	public void setMobile(java.lang.String mobile) {	    this.mobile=mobile;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}
}

