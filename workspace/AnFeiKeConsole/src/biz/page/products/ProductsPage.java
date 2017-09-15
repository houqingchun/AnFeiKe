package biz.page.products;

import biz.entity.photos.Photos;

import com.base.page.BasePage;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>ProductsPage<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class ProductsPage extends BasePage {

	private java.lang.String id;// 主键
	private java.lang.String name;// 机型型号
	private java.lang.String types;// 类别
	private java.lang.String brand;// 品牌
	private java.lang.Integer seatsNo;// 座位数
	private java.lang.Integer ranges;// 续航里程
	private java.lang.String rangesUnit;// 里程单位
	private java.lang.Integer speeds;// 速度
	private java.lang.String speedsUnit;// 速度单位
	private java.lang.Double loads;// 最大载重
	private java.lang.String loadsUnit;// 最大载重单位
	private java.lang.String photoPath;// 照片
	private java.lang.String shortDesc;// 简述
	private java.lang.String description;// 详细描述
	private java.lang.String manufacture;// 制造商
	private java.util.Date produceDate;// 生产日期
	private java.lang.Double price;// 参考价
	private java.lang.String priceUnit;// 参考价单位
	private java.lang.Double prePay;// 预付价
	private java.lang.String prePayUnit;// 预付价单位
	private java.lang.String createBy;// 创建人
	private java.util.Date createTime;// 创建时间
	private java.lang.String updateBy;// 更新人
	private java.util.Date updateTime;// 修改时间
	private List<Photos> photos;

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

	public java.lang.String getTypes() {
		return this.types;
	}

	public void setTypes(java.lang.String types) {
		this.types = types;
	}

	public java.lang.String getBrand() {
		return this.brand;
	}

	public void setBrand(java.lang.String brand) {
		this.brand = brand;
	}

	public java.lang.Integer getSeatsNo() {
		return this.seatsNo;
	}

	public void setSeatsNo(java.lang.Integer seatsNo) {
		this.seatsNo = seatsNo;
	}

	public java.lang.Integer getRanges() {
		return this.ranges;
	}

	public void setRanges(java.lang.Integer ranges) {
		this.ranges = ranges;
	}

	public java.lang.String getRangesUnit() {
		return this.rangesUnit;
	}

	public void setRangesUnit(java.lang.String rangesUnit) {
		this.rangesUnit = rangesUnit;
	}

	public java.lang.Integer getSpeeds() {
		return this.speeds;
	}

	public void setSpeeds(java.lang.Integer speeds) {
		this.speeds = speeds;
	}

	public java.lang.String getSpeedsUnit() {
		return this.speedsUnit;
	}

	public void setSpeedsUnit(java.lang.String speedsUnit) {
		this.speedsUnit = speedsUnit;
	}

	public java.lang.Double getLoads() {
		return this.loads;
	}

	public void setLoads(java.lang.Double loads) {
		this.loads = loads;
	}

	public java.lang.String getLoadsUnit() {
		return this.loadsUnit;
	}

	public void setLoadsUnit(java.lang.String loadsUnit) {
		this.loadsUnit = loadsUnit;
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

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getManufacture() {
		return this.manufacture;
	}

	public void setManufacture(java.lang.String manufacture) {
		this.manufacture = manufacture;
	}

	public java.util.Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(java.util.Date produceDate) {
		this.produceDate = produceDate;
	}

	public java.lang.Double getPrice() {
		return this.price;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	public java.lang.String getPriceUnit() {
		return this.priceUnit;
	}

	public void setPriceUnit(java.lang.String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public java.lang.Double getPrePay() {
		return this.prePay;
	}

	public void setPrePay(java.lang.Double prePay) {
		this.prePay = prePay;
	}

	public java.lang.String getPrePayUnit() {
		return this.prePayUnit;
	}

	public void setPrePayUnit(java.lang.String prePayUnit) {
		this.prePayUnit = prePayUnit;
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

	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

}
