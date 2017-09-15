package com.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.SerializationUtils;

public class BeanUtil {

	// 比较两个对象,找出值不同的字段
	public static Map<String, Map<String, String>> compareObjects(
			Object oldObject, Object newObject, List<String> comparedFieldsList) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
		BeanMap map = new BeanMap(oldObject);
		PropertyUtilsBean propUtils = new PropertyUtilsBean();

		for (Object propNameObject : map.keySet()) {
			String propertyName = (String) propNameObject;
			if (propertyName.equals("createBy")
					|| propertyName.equals("createTime")
					|| propertyName.equals("updateBy")
					|| propertyName.equals("updateTime")
					|| propertyName.equals("companyCode")
					|| propertyName.equals("languageCode")
					|| propertyName.equals("languageCd")
					|| !comparedFieldsList.contains(propertyName)
					) {
				continue;
			}
			Object property1 = propUtils.getProperty(oldObject, propertyName);
			Object property2 = propUtils.getProperty(newObject, propertyName);
			if (property1 == null && property2 == null) {
				continue;
			} else if (property1 != null && property2 != null) {
				if (property1 instanceof BigDecimal
						|| property2 instanceof BigDecimal) {
					BigDecimal bg1, bg2;
					bg1 = (BigDecimal) property1;
					bg2 = (BigDecimal) property2;
					/*
					 * bg1 = bg1 ==null? BigDecimal.ZERO:bg1; bg2 = bg2 ==null?
					 * BigDecimal.ZERO:bg2;
					 */
					if (bg1.compareTo(bg2) != 0) {
						getDifferProperty(resultMap, propertyName, bg1, bg2);
					}
				} else if (!property1.equals(property2)) {
					getDifferProperty(resultMap, propertyName, property1,
							property2);
				}
			} else {
				getDifferProperty(resultMap, propertyName, property1, property2);
			}

		}
		return resultMap;
	}

	private static void getDifferProperty(
			Map<String, Map<String, String>> resultMap, String propertyName,
			Object property1, Object property2) {
		Map<String, String> values = new HashMap<String, String>();
		String oldValue = "";
		String newValue = "";
		if (property1 instanceof Date || property2 instanceof Date) {
			oldValue = DateUtil.getDateLong((Date) property1);
			newValue = DateUtil.getDateLong((Date) property2);
		} else if (property1 instanceof Timestamp
				|| property2 instanceof Timestamp) {
			oldValue = DateUtil.getPlusTime((Timestamp) property1);
			newValue = DateUtil.getPlusTime((Timestamp) property2);
		} else if (property1 instanceof String[]
				|| property2 instanceof String[]) {
			if (null != property1) {
				for (String str : (String[]) property1) {
					oldValue += str + ";";
				}
			}
			if (null != property2) {
				for (String str : (String[]) property2) {
					newValue += str + ";";
				}
			}
		} else {
			oldValue = String.valueOf(property1);
			newValue = String.valueOf(property2);
			if (null == property1) {
				oldValue = "";
			}
			if (null == property2) {
				newValue = "";
			}
		}

		if (!oldValue.equals(newValue)) {
			values.put("oldValue", oldValue);
			values.put("newValue", newValue);
			resultMap.put(propertyName, values);
		}
	}
	
	/**
	 * 将指定对象序列化到指定路径下
	 * @param obj
	 * @param objectFilePath
	 * @return
	 */
	public static boolean serializeObject(Serializable obj, String folderPath, String fileName){
		try {
			File folder = new File(folderPath);
			if (!folder.exists()){
				folder.mkdirs();
			}
			SerializationUtils.serialize(obj, new FileOutputStream(folderPath + "/" + fileName));
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将指定路径下的对象反序列化
	 * @param objectFilePath
	 * @return
	 */
	public static Object deserializeObject(String folderPath, String fileName){
		try {
			return SerializationUtils.deserialize(new FileInputStream(folderPath + "/" + fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
//		Inspection oldInspection = new Inspection();
//		oldInspection.setId("1");
//		oldInspection.setInstrumentThershold(1015);
//		Inspection newInspection = new Inspection();
//		newInspection.setId("1");
//		newInspection.setInstrumentThershold(2015);
//		List<String> fieldListConverted = new ArrayList<String>();
//		
//		Map<String, Map<String, String>> diffMap = BeanUtil.compareObjects(oldInspection, newInspection, fieldListConverted);
//		
//		// 遍历比较结果，把变化的属性检测为检测变更实体，等待后续保存
//		for (Map.Entry<String, Map<String, String>> entry : diffMap.entrySet()) {
//			Map<String, String> v = entry.getValue();
//			System.out.print(v.get("newValue"));
//			System.out.println(v.get("oldValue"));
//			
//		}
		
//		String fileName = "/fjiaf/fwjfi/2929404A.XlS";
//		String s = fileName.substring(0, fileName.lastIndexOf(".")) + fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		String s = "D:/testwork/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/GW/serialized_object/";
		
		File file = new File(s);
		
		if (!file.exists()){
			file.mkdirs();
		}
		System.out.println(new FileOutputStream(s + "f6858a4a-2ebf-4906-84e7-ea7387c1d9eb.db"));
	}
}
