package biz.entity.band;

import java.util.List;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>BandEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class Band extends BaseEntity {
	
	
	private java.lang.String status;//   状态
	private java.lang.String styleComment;//   风格备注
	private java.lang.String contact;//  联系人
	private java.lang.String mobile;//  联系人电话
	private java.lang.Integer hit;//   点击率
	private java.lang.Integer integrity;//   完整度
		
	public java.lang.String getId() {
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getStyleComment() {
		return styleComment;
	}
	public void setStyleComment(java.lang.String styleComment) {
		this.styleComment = styleComment;
	}
	public java.lang.String getContact() {
		return contact;
	}
	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}
	public java.lang.String getMobile() {
		return mobile;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	public java.lang.Integer getHit() {
		return hit;
	}
	public void setHit(java.lang.Integer hit) {
		this.hit = hit;
	}
	public java.lang.Integer getIntegrity() {
		return integrity;
	}
	public void setIntegrity(java.lang.Integer integrity) {
		this.integrity = integrity;
	}
}
