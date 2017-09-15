package biz.page.dict;

import com.base.page.BasePage;
import java.math.BigDecimal;

/**
 * 
 * <br>
 * <b>功能：</b>DataDictionaryPage<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
public class DataDictionaryPage extends BasePage {
	

		private java.lang.String id;//   ID	private java.lang.String code;//   对象值	private java.lang.String type;//   数据字典类型	private java.lang.String name;//   对象值描述	private java.lang.Integer nameNumber;//   对象序号	private java.sql.Timestamp createTime;//   创建时间	private java.lang.String createBy;//   创建人	private java.sql.Timestamp updateTime;//   修改时间	private java.lang.String updateBy;//   修改人	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getCode() {	    return this.code;	}	public void setCode(java.lang.String code) {	    this.code=code;	}	public java.lang.String getType() {	    return this.type;	}	public void setType(java.lang.String type) {	    this.type=type;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.Integer getNameNumber() {	    return this.nameNumber;	}	public void setNameNumber(java.lang.Integer nameNumber) {	    this.nameNumber=nameNumber;	}	public java.sql.Timestamp getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.sql.Timestamp createTime) {	    this.createTime=createTime;	}	public java.lang.String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.String createBy) {	    this.createBy=createBy;	}	public java.sql.Timestamp getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.sql.Timestamp updateTime) {	    this.updateTime=updateTime;	}	public java.lang.String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.String updateBy) {	    this.updateBy=updateBy;	}
	
}
