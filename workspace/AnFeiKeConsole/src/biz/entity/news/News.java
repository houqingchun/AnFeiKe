package biz.entity.news;

import biz.entity.photos.Photos;

import com.base.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;
/**
 * 
 * <br>
 * <b>功能：</b>NewsEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class News extends BaseEntity {
	
		private java.lang.String id;//   主键	private java.lang.String subject;//   标题	private java.lang.String photoPath;//   照片	private java.lang.String status;//   状态	private java.lang.String types;//   类别	private java.lang.String onTop;//   置顶	private java.lang.String description;//   详细描述	private java.lang.String createBy;//   创建人	private java.util.Date createTime;//   创建时间	private java.lang.String updateBy;//   更新人	private java.util.Date updateTime;//   修改时间
	private List<Photos> photos;	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getSubject() {	    return this.subject;	}	public void setSubject(java.lang.String subject) {	    this.subject=subject;	}	public java.lang.String getPhotoPath() {	    return this.photoPath;	}	public void setPhotoPath(java.lang.String photoPath) {	    this.photoPath=photoPath;	}	public java.lang.String getStatus() {	    return this.status;	}	public void setStatus(java.lang.String status) {	    this.status=status;	}	public java.lang.String getTypes() {	    return this.types;	}	public void setTypes(java.lang.String types) {	    this.types=types;	}	public java.lang.String getOnTop() {	    return this.onTop;	}	public void setOnTop(java.lang.String onTop) {	    this.onTop=onTop;	}	public java.lang.String getDescription() {	    return this.description;	}	public void setDescription(java.lang.String description) {	    this.description=description;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
}

