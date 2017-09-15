package biz.page.band;

import com.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>BandPage<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class BandPage extends BasePage {
	

		private java.lang.String id;//   主键	private java.lang.String name;//   乐队名称	private java.lang.String photoPath;//   乐队照片
	private java.lang.String status;//   状态	private java.lang.String province;//   省份	private java.lang.String city;//   城市	private java.lang.String style;//   风格
	private java.lang.String styleComment;//   风格备注	private java.lang.String formation;//   编制	private java.lang.String staffComposition;//   人员组成	private java.lang.String leaderGender;//   主唱性别	private java.lang.String isOriginal;//   是否原创	private java.lang.String isBiz;//   是否接受商演	private java.lang.String isStationed;//   是否可以驻场	private java.lang.String isAcademic;//   是否学院派	private java.lang.String qualification;//   资质证明	private java.lang.Double lowerPrice;//   演出价格（最低价）	private java.lang.Double highPrice;//   演出价格（最高价）	private java.lang.String priceUnit;//   价格单位	private java.lang.String description;//   乐队介绍
	private java.lang.String contact;//  联系人
	private java.lang.String mobile;//  联系人电话
	private java.lang.Integer hit;//   点击率
	private java.lang.Integer integrity;//   完整度	private java.lang.String createBy;//   创建人	private java.util.Date createTime;//   创建时间	private java.lang.String updateBy;//   更新人	private java.util.Date updateTime;//   修改时间	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.String getPhotoPath() {	    return this.photoPath;	}	public void setPhotoPath(java.lang.String photoPath) {	    this.photoPath=photoPath;	}	public java.lang.String getProvince() {	    return this.province;	}	public void setProvince(java.lang.String province) {	    this.province=province;	}	public java.lang.String getCity() {	    return this.city;	}	public void setCity(java.lang.String city) {	    this.city=city;	}	public java.lang.String getStyle() {	    return this.style;	}	public void setStyle(java.lang.String style) {	    this.style=style;	}	public java.lang.String getFormation() {	    return this.formation;	}	public void setFormation(java.lang.String formation) {	    this.formation=formation;	}	public java.lang.String getStaffComposition() {	    return this.staffComposition;	}	public void setStaffComposition(java.lang.String staffComposition) {	    this.staffComposition=staffComposition;	}	public java.lang.String getLeaderGender() {	    return this.leaderGender;	}	public void setLeaderGender(java.lang.String leaderGender) {	    this.leaderGender=leaderGender;	}	public java.lang.String getIsOriginal() {	    return this.isOriginal;	}	public void setIsOriginal(java.lang.String isOriginal) {	    this.isOriginal=isOriginal;	}	public java.lang.String getIsBiz() {	    return this.isBiz;	}	public void setIsBiz(java.lang.String isBiz) {	    this.isBiz=isBiz;	}	public java.lang.String getIsStationed() {	    return this.isStationed;	}	public void setIsStationed(java.lang.String isStationed) {	    this.isStationed=isStationed;	}	public java.lang.String getIsAcademic() {	    return this.isAcademic;	}	public void setIsAcademic(java.lang.String isAcademic) {	    this.isAcademic=isAcademic;	}	public java.lang.String getQualification() {	    return this.qualification;	}	public void setQualification(java.lang.String qualification) {	    this.qualification=qualification;	}	public java.lang.Double getLowerPrice() {	    return this.lowerPrice;	}	public void setLowerPrice(java.lang.Double lowerPrice) {	    this.lowerPrice=lowerPrice;	}	public java.lang.Double getHighPrice() {	    return this.highPrice;	}	public void setHighPrice(java.lang.Double highPrice) {	    this.highPrice=highPrice;	}	public java.lang.String getPriceUnit() {	    return this.priceUnit;	}	public void setPriceUnit(java.lang.String priceUnit) {	    this.priceUnit=priceUnit;	}	public java.lang.String getDescription() {	    return this.description;	}	public void setDescription(java.lang.String description) {	    this.description=description;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}
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
