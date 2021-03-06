package biz.entity.company;

import com.base.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>CompanyEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class Company extends BaseEntity {

	private java.lang.String id;// 主键
	private java.lang.String name;// 公司名称
	private java.lang.String photoPath;// 图标
	private java.lang.String shortDesc;// 公司简介
	private java.lang.String serviceAim;// 服务宗旨
	private java.lang.String slogan;// 口号
	private java.lang.String longDesc;// 公司详细介绍
	private java.lang.String address;// 联系地址
	private java.lang.String zip;// 邮编
	private java.lang.String tel;// 座机
	private java.lang.String mobile;// 移动电话
	private java.lang.String contact;// 联系人
	private java.lang.String hotlines;// 咨询热线
	private java.lang.String email;// 邮件
	private java.lang.String icp;// 备案号
	private java.lang.String training;// 培训
	private java.lang.String trusteeship;// 托管
	private java.lang.String rent;// 租赁
	private java.lang.String sale;// 销售
	private java.lang.String desc1;// 业务1
	private java.lang.String desc2;// 业务2
	private java.lang.String desc3;// 业务3
	private java.lang.String desc4;// 业务4
	private java.lang.String desc5;// 业务5
	private java.lang.String createBy;// 创建人
	private java.util.Date createTime;// 创建时间
	private java.lang.String updateBy;// 更新人
	private java.util.Date updateTime;// 修改时间

	public java.lang.String getId() {
		return this.id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getHotlines() {
		return hotlines;
	}

	public void setHotlines(java.lang.String hotlines) {
		this.hotlines = hotlines;
	}

	public java.lang.String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(java.lang.String photoPath) {
		this.photoPath = photoPath;
	}

	public java.lang.String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(java.lang.String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public java.lang.String getServiceAim() {
		return this.serviceAim;
	}

	public void setServiceAim(java.lang.String serviceAim) {
		this.serviceAim = serviceAim;
	}

	public java.lang.String getSlogan() {
		return this.slogan;
	}

	public void setSlogan(java.lang.String slogan) {
		this.slogan = slogan;
	}

	public java.lang.String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(java.lang.String longDesc) {
		this.longDesc = longDesc;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getZip() {
		return this.zip;
	}

	public void setZip(java.lang.String zip) {
		this.zip = zip;
	}

	public java.lang.String getTel() {
		return this.tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getContact() {
		return this.contact;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getIcp() {
		return this.icp;
	}

	public void setIcp(java.lang.String icp) {
		this.icp = icp;
	}

	public java.lang.String getTraining() {
		return this.training;
	}

	public void setTraining(java.lang.String training) {
		this.training = training;
	}

	public java.lang.String getTrusteeship() {
		return this.trusteeship;
	}

	public void setTrusteeship(java.lang.String trusteeship) {
		this.trusteeship = trusteeship;
	}

	public java.lang.String getRent() {
		return this.rent;
	}

	public void setRent(java.lang.String rent) {
		this.rent = rent;
	}

	public java.lang.String getSale() {
		return this.sale;
	}

	public void setSale(java.lang.String sale) {
		this.sale = sale;
	}

	public java.lang.String getDesc1() {
		return this.desc1;
	}

	public void setDesc1(java.lang.String desc1) {
		this.desc1 = desc1;
	}

	public java.lang.String getDesc2() {
		return this.desc2;
	}

	public void setDesc2(java.lang.String desc2) {
		this.desc2 = desc2;
	}

	public java.lang.String getDesc3() {
		return this.desc3;
	}

	public void setDesc3(java.lang.String desc3) {
		this.desc3 = desc3;
	}

	public java.lang.String getDesc4() {
		return this.desc4;
	}

	public void setDesc4(java.lang.String desc4) {
		this.desc4 = desc4;
	}

	public java.lang.String getDesc5() {
		return this.desc5;
	}

	public void setDesc5(java.lang.String desc5) {
		this.desc5 = desc5;
	}

	public java.lang.String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
}
