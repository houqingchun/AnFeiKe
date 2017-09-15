package biz.entity.evaluations;

import com.base.entity.BaseEntity;
import java.math.BigDecimal;
/**
 * 
 * <br>
 * <b>功能：</b>EvaluationsEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class Evaluations extends BaseEntity {
	
		private java.lang.String id;//   id主键	private java.lang.String description;//   评论内容	private java.lang.String mainId;//   主表ID	private java.lang.String mainType;//   主表类别	private java.lang.String isAvailable;//   是否可用	private java.lang.Integer score;//   分值	private java.lang.String isDeleted;//   是否删除
	private java.lang.String ip;//   IP地址	private java.util.Date createTime;//   创建时间	private java.util.Date updateTime;//   修改时间	private java.lang.String createBy;//   创建人	private java.lang.String updateBy;//   修改人	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getDescription() {	    return this.description;	}	public void setDescription(java.lang.String description) {	    this.description=description;	}	public java.lang.String getMainId() {	    return this.mainId;	}	public void setMainId(java.lang.String mainId) {	    this.mainId=mainId;	}	public java.lang.String getMainType() {	    return this.mainType;	}	public void setMainType(java.lang.String mainType) {	    this.mainType=mainType;	}	public java.lang.String getIsAvailable() {	    return this.isAvailable;	}	public void setIsAvailable(java.lang.String isAvailable) {	    this.isAvailable=isAvailable;	}	public java.lang.Integer getScore() {	    return this.score;	}	public void setScore(java.lang.Integer score) {	    this.score=score;	}	public java.lang.String getIsDeleted() {	    return this.isDeleted;	}	public void setIsDeleted(java.lang.String isDeleted) {	    this.isDeleted=isDeleted;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}
	public java.lang.String getIp() {
		return ip;
	}
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
}

