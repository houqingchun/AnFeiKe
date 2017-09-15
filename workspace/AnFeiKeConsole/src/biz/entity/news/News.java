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
	
	
	private List<Photos> photos;
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
}
