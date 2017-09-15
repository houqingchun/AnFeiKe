package biz.page.slide;

import com.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>SlidePage<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class SlidePage extends BasePage {
	

		private java.lang.String id;//   主键	private java.lang.String slideType;//   幻灯片类别	private java.lang.String themeTitle;//   标题	private java.lang.String photoPath;//   图片	private java.lang.String themeDesc;//   主题内容	private java.lang.String themeComments;//   注释	private java.lang.Integer displayOrder;//   显示顺序	private java.lang.String createBy;//   创建人	private java.util.Date createTime;//   创建时间	private java.lang.String updateBy;//   更新人	private java.util.Date updateTime;//   修改时间	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getSlideType() {	    return this.slideType;	}	public void setSlideType(java.lang.String slideType) {	    this.slideType=slideType;	}	public java.lang.String getThemeTitle() {	    return this.themeTitle;	}	public void setThemeTitle(java.lang.String themeTitle) {	    this.themeTitle=themeTitle;	}	public java.lang.String getPhotoPath() {	    return this.photoPath;	}	public void setPhotoPath(java.lang.String photoPath) {	    this.photoPath=photoPath;	}	public java.lang.String getThemeDesc() {	    return this.themeDesc;	}	public void setThemeDesc(java.lang.String themeDesc) {	    this.themeDesc=themeDesc;	}	public java.lang.String getThemeComments() {	    return this.themeComments;	}	public void setThemeComments(java.lang.String themeComments) {	    this.themeComments=themeComments;	}	public java.lang.Integer getDisplayOrder() {	    return this.displayOrder;	}	public void setDisplayOrder(java.lang.Integer displayOrder) {	    this.displayOrder=displayOrder;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}
	
}
