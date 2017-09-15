package biz.entity.photos;

import com.base.entity.BaseEntity;
import java.math.BigDecimal;
/**
 * 
 * <br>
 * <b>功能：</b>PhotosEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class Photos extends BaseEntity {
	
		private java.lang.String id;//   主键	private java.lang.String photoPath;//   照片	private java.lang.String description;//   介绍	private java.lang.String mainId;//   主表ID	private java.lang.String mainType;//   主表类别	private java.lang.String createBy;//   创建人	private java.util.Date createTime;//   创建时间	private java.lang.String updateBy;//   更新人	private java.util.Date updateTime;//   修改时间	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getPhotoPath() {	    return this.photoPath;	}	public void setPhotoPath(java.lang.String photoPath) {	    this.photoPath=photoPath;	}	public java.lang.String getDescription() {	    return this.description;	}	public void setDescription(java.lang.String description) {	    this.description=description;	}	public java.lang.String getMainId() {	    return this.mainId;	}	public void setMainId(java.lang.String mainId) {	    this.mainId=mainId;	}	public java.lang.String getMainType() {	    return this.mainType;	}	public void setMainType(java.lang.String mainType) {	    this.mainType=mainType;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}
}

