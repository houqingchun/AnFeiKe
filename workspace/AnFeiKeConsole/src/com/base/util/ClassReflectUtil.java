package com.base.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.base.service.BaseService;

public class ClassReflectUtil {

	private static final Log log = LogFactory.getLog(ClassReflectUtil.class);

	/**
	 * 根据类名反射创建对象
	 * 
	 * @param name
	 *            类名
	 * @return 对象
	 * @throws Exception
	 */
	public static Object getInstance(String name) throws Exception {
		Class<?> cls = Class.forName(name);
		return cls.newInstance();
	}

	/**
	 * 根据字段名转换为java bean属性名
	 * 
	 * @param columnName
	 * @return
	 */
	public static String convertToBeanFieldName(String columnName) {
		String[] split = columnName.split("_");
		if (split.length >= 1) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				String tempFieldName = "";
				if (i == 0) {
					tempFieldName = split[i].substring(0, 1).toLowerCase()
							+ split[i].substring(1, split[i].length()).toLowerCase();
				} else {
					tempFieldName = split[i].substring(0, 1).toUpperCase()
							+ split[i].substring(1, split[i].length()).toLowerCase();
				}

				sb.append(tempFieldName);
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 根据表名转换为类名
	 * 
	 * @param tableName
	 * @return
	 */
	public static String convertToClassName(String tableName) {
		String[] split = tableName.split("_");
		if (split.length >= 1) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				String tempTableName = split[i].substring(0, 1).toUpperCase()
						+ split[i].substring(1, split[i].length()).toLowerCase();
				sb.append(tempTableName);
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 根据实体类名转换为名表
	 * 
	 * @param tableName
	 * @return
	 */
	public static String convertToTableName(String entityClassName) {
		String className = entityClassName.substring(0, 1).toLowerCase() + entityClassName.substring(1);
		return convertToColumnName(className);
	}

	/**
	 * 反射方法，打印对象的属性，方法，构造器属性
	 * 
	 * @param obj
	 *            被反射对象
	 */
	public static void reflect(Object obj) {
		Class<?> cls = obj.getClass();
		log.debug("************构  造  器************");
		Constructor<?>[] constructors = cls.getConstructors();
		for (Constructor<?> constructor : constructors) {
			log.debug("构造器名称:" + constructor.getName() + "\t" + "    " + "构造器参数类型:"
					+ Arrays.toString(constructor.getParameterTypes()));
		}
		log.debug("************属     性************");
		Field[] fields = cls.getDeclaredFields();
		// cls.getFields() 该方法只能访问共有的属性
		// cls.getDeclaredFields() 可以访问私有属性
		for (Field field : fields) {
			log.debug("属性名称:" + field.getName() + "\t" + "属性类型:" + field.getType() + "\t");
		}
		log.debug("************方   法************");
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			log.debug("方法名:" + method.getName() + "\t" + "方法返回类型：" + method.getReturnType() + "\t" + "方法参数类型:"
					+ Arrays.toString(method.getParameterTypes()));
		}
	}

	/**
	 * 
	 * @param obj
	 *            访问对象
	 * @param filedname
	 *            对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object getFieldValue(Object obj, String filedname) throws Exception {
		// 反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		// 反射出类型字段
		try {
			field = cls.getDeclaredField(filedname);
		} catch (Exception e) {
			// e.printStackTrace();
			log.debug("没有这个字段(GetValue)：[" + obj.getClass().getSimpleName() + "]-" + filedname);
			return null;
		}

		// 获取属性时，压制Java对访问修饰符的检查
		field.setAccessible(true);
		// 在对象obj上读取field属性的值
		Object val = field.get(obj);
		return val;
	}

	/**
	 * 判断是否包含特定属性
	 * @param obj
	 * @param filedname
	 * @return
	 */
	public static boolean hasField(Object obj, String filedname) {
		// 反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		// 反射出类型字段
		try {
			field = cls.getDeclaredField(filedname);
		} catch (Exception e) {
			// e.printStackTrace();
			log.debug("没有这个字段(GetValue)：[" + obj.getClass().getSimpleName() + "]-" + filedname);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param obj
	 *            访问对象
	 * @param filedname
	 *            对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object setIdKeyValue(Object obj, String filedname, String value) throws Exception {
		// 反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		// 反射出类型字段
		try {
			field = cls.getDeclaredField(filedname);
		} catch (Exception e) {
			// e.printStackTrace();
			log.debug("没有这个字段(SetValue)：[" + obj.getClass().getSimpleName() + "]-" + filedname);
		}
		if (field == null) {
			return null;
		}
		// 获取属性时，压制Java对访问修饰符的检查
		field.setAccessible(true);
		// ---------------------------------------------------
		// 针对表主键为字符类型进行赋值UUID,如果为int类型采用自增方式
		if (!field.getType().getName().contains("Integer")) {
			field.set(obj, value);
		}
		// ---------------------------------------------------
		// 在对象obj上读取field属性的值
		Object val = field.get(obj);
		field.setAccessible(false);
		return val;
	}

	/**
	 * 获取对象中属性类型
	 * 
	 * @param obj
	 * @param filedname1
	 * @return
	 */
	public static String getFieldType(Object obj, String filedname1) {
		Class<?> cls = obj.getClass();
		Field field = null;
		String filedname = null;
		String subFieldName;
		if (filedname1.indexOf(".") > 0) {
			filedname = filedname1.substring(0, filedname1.indexOf("."));
			subFieldName = filedname1.substring(filedname1.indexOf(".") + 1);
		} else {
			filedname = filedname1;
			subFieldName = null;
		}

		// 反射出类型字段
		try {
			field = cls.getDeclaredField(filedname);
			// 在对象obj上定稿field属性的值
			Class<?> classObj = Class.forName(field.getType().getName());
			if (subFieldName != null) {
				Object inst = getFieldValue(obj, filedname);
				if (inst == null) {
					inst = classObj.newInstance();
				}

				return getFieldType(inst, subFieldName);
			} else {
				return field.getType().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("获取类型失败(getFieldType)：[" + obj.getClass().getSimpleName() + "]-" + filedname);
		}

		return null;
	}

	/**
	 * 
	 * @param obj
	 *            访问对象
	 * @param filedname
	 *            对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object setFieldValue(Object obj, String filedname1, Object value) throws Exception {
		// 反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		String filedname = null;
		String subFieldName;
		if (filedname1.indexOf(".") > 0) {
			filedname = filedname1.substring(0, filedname1.indexOf("."));
			subFieldName = filedname1.substring(filedname1.indexOf(".") + 1);
		} else {
			filedname = filedname1;
			subFieldName = null;
		}

		// 反射出类型字段
		try {
			field = cls.getDeclaredField(filedname);
			// 获取属性时，压制Java对访问修饰符的检查
			field.setAccessible(true);
			// 在对象obj上定稿field属性的值
			// Object val = field.get(obj);

			Class<?> classObj = Class.forName(field.getType().getName());
			if (subFieldName != null) {
				Object inst = getFieldValue(obj, filedname);
				if (inst == null) {
					inst = classObj.newInstance();
				}

				setFieldValue(inst, subFieldName, value);
				field.set(obj, inst);
				field.setAccessible(false);
			} else {
				field.set(obj, value);
				field.setAccessible(false);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.debug("没有这个字段(SetValue)：[" + obj.getClass().getSimpleName() + "]-" + filedname);
		}
		if (field == null) {
			return null;
		}

		return true;
	}

	/**
	 * 反射调用对象的方法
	 * 
	 * @param obj
	 *            对象
	 * @param methodName
	 *            方法名称
	 * @param paramType
	 *            参数类型 new Class[]{int.class,double.class}
	 * @param params
	 *            参数值 new Object[]{2,3.5}
	 * @return
	 * @throws Exception
	 */
	public static Object readObjMethod(Object obj, String methodName, Class<?>[] paramTypes, Object[] params)
			throws Exception {
		// 发现类型
		Class<?> cls = obj.getClass();
		// 发现方法
		Method method = cls.getDeclaredMethod(methodName, paramTypes);
		// 访问方法时,压制Java对访问修饰符的检查
		method.setAccessible(true);
		Object val = method.invoke(obj, params);
		return val;
	}

	public static void main(String[] args) {
		person p = new person();
//		p.setName("12");
//		System.out.println(getFieldType(p, "name").toString());
		// try {
		//
		// log.debug(setFieldValue(p, "course.id", "9240240"));
		// log.debug(setFieldValue(p, "course.date", new Date()));
		// Thread.sleep(1000);
		// log.debug(setFieldValue(p, "date", new Date()));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(p.getCourse().getId());
		// System.out.println(p.getCourse().getDate());
		// System.out.println(p.getDate());
		// System.out.println(convertToTableName("ComponentInfo"));
		// System.out.println(convertToClassName("component_info"));
	}

	public static String convertToColumnName(String fieldName) {
		String columnName = "";
		for (int i = 0; i < fieldName.length(); i++) {
			char c = fieldName.charAt(i);
			if (c >='A' && c <= 'Z') {
				columnName += "_" + (char) (c + 32);
			} else {
				columnName += c;
			}

		}
		return columnName;
	}
}

class person {
	private String name;
	private Date date;
	private Course course;

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}

class Course {
	private String id;
	private Date date;

	public Course() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
