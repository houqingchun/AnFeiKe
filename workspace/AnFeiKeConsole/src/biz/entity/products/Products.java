package biz.entity.products;

import java.util.List;

import biz.entity.photos.Photos;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>ProductsEntity<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class Products extends BaseEntity {
	
	
	private List<Photos> photos;
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
}
