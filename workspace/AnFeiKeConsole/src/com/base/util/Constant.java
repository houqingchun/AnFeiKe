package com.base.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Constant {

	/**
	 * 批量执行插入时的最大数量
	 */
	public final static int BATCH_PROCESS_MAX_NBR_INSERT = 2000;
	/**
	 * 批量执行更新时的最大数量
	 */
	public final static int BATCH_PROCESS_MAX_NBR_UPDATE = 2000;
	/**
	 * 批量执行删除时的最大数量
	 */
	public final static int BATCH_PROCESS_MAX_NBR_DELETE = 10000;
	/**
	 * 批量执行选择时最大数量
	 */
	public final static int BATCH_PROCESS_MAX_NBR_SELECT = 10000;

	/**
	 * 数据字典类型-机型类型
	 */
	public final static String DICT_TYPE_PRODUCTTYPE = "DICT_TYPE_PRODUCTTYPE";
	
	public final static String DICT_TYPE_BRANDS = "DICT_TYPE_BRANDS";
	
	
	public final static String DICT_TYPE_SLIDETYPE = "DICT_TYPE_SLIDETYPE";
	public final static String DICT_TYPE_SLIDETYPE_HOME = "TYPE1";
	public final static String DICT_TYPE_SLIDETYPE_COMP = "TYPE2";
	
	public final static String DICT_TYPE_YES_OR_NO = "DICT_TYPE_YES_OR_NO";
	

	public final static String DICT_TYPE_NEWSTYPE = "DICT_TYPE_NEWSTYPE";
	public final static String DICT_TYPE_NEWSTYPE_COMPANY = "TYPE1";
	public final static String DICT_TYPE_NEWSTYPE_AREA = "TYPE2";
	public final static String DICT_TYPE_NEWSTYPE_POLICY = "TYPE3";
	
	/**
	 * 错误处理-下一步操作编码
	 */
	public final static String ERROR_NEXT_STEP_LOGON = "LOGON";
	
	/**
	 * 发布状态-草稿
	 */
	public final static String PUBLISH_STATUS_DRAFT = "DRAFT";
	/**
	 * 发布状态-已发布
	 */
	public final static String PUBLISH_STATUS_PUBLISHED = "PUBLISHED";
	

	/**
	 * 数据查询时最大查询记录数
	 */
	public final static int MAX_PAGESIZE = 65536;

	// 上传文件临时目录
	public final static String UPLOAD_FOLDER_TMP = "upload_tmp";
	
	// 上传文件主目录
	public final static String UPLOAD_FOLDER = "upload";
	
	
	/**
	 * HTML静态化要目录
	 */
	public final static String HTML_ROOT = "html";
	
	public final static String HTML_FILE_TYPE_LIST = "LIST";
	
	public final static String HTML_FILE_TYPE_EDIT = "EDIT";
	
	/**
	 * 图片绽放尺寸定义
	 */
	public final static int IMAGE_SCALE_W_LARGE = 800;	
	public final static int IMAGE_SCALE_H_LARGE = 600;
	
	public final static int IMAGE_SCALE_W_MEDIUM = 480;	
	public final static int IMAGE_SCALE_H_MEDIUM = 360;
	
	public final static int IMAGE_SCALE_W_SMALL = 280;	
	public final static int IMAGE_SCALE_H_SMALL = 210;
	
	public final static int IMAGE_SCALE_W_ICON = 80;	
	public final static int IMAGE_SCALE_H_ICON = 60;
	
	public final static String I18N_FILE_NAME = "i18nmsg";
	
	public final static int PAGINATION_NBR_SHOWN = 6;//显示页码的个数
	
	public final static int PAGINATION_ROWS_PER_PAGE = 32;//每页显示记录条数
	
	public final static String PUBLISH_CONTEXT_PATH = MyConfig.getText("publish.context.path");
	
	/**
	 * 超级管理员常量
	 * 
	 * @author www.jeecg.org
	 *
	 */
	public static enum SuperAdmin {
		NO("N", "否"), YES("Y", "是");
		public String key;
		public String value;

		private SuperAdmin(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public static SuperAdmin get(int key) {
			SuperAdmin[] values = SuperAdmin.values();
			for (SuperAdmin object : values) {
				if (object.key.equals(key)) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 状态枚举
	 * 
	 * @author www.jeecg.org
	 *
	 */
	public static enum STATE {
		ENABLE("Y", "可用"), DISABLE("N", "禁用");
		public String key;
		public String value;

		private STATE(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public static STATE get(int key) {
			STATE[] values = STATE.values();
			for (STATE object : values) {
				if (object.key.equals(key)) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 删除枚举
	 * 
	 * @author www.jeecg.org
	 *
	 */
	public static enum DELETED {
		NO("N", "未删除"), YES("Y", "已删除");
		public String key;
		public String value;

		private DELETED(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public static DELETED get(int key) {
			DELETED[] values = DELETED.values();
			for (DELETED object : values) {
				if (object.key.equals(key)) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 枚举
	 * 
	 * @author www.jeecg.org
	 *
	 */
	public static enum RelType {
		MENU("0", "菜单"), USER("1", "用户"), BTN("2", "按钮");
		public String key;
		public String value;

		private RelType(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public static RelType get(String key) {
			RelType[] values = RelType.values();
			for (RelType object : values) {
				if (object.key.equals(key)) {
					return object;
				}
			}
			return null;
		}
	}

	// 数据字典标记
	public final static String CACHE_DATADICT = "CACHE_DATADICT";
	
	public final static String CACHE_COMPANY = "CACHE_COMPANY";
	
	// 数据字典标记
	public final static String CACHE_TBL_KEY_MAP = "CACHE_TBL_KEY_MAP";
}

class MyConfig{
	private static ResourceBundle bundle = ResourceBundle.getBundle("config");
	public static String getText(String key){		
		return bundle.getString(key);
	}
}