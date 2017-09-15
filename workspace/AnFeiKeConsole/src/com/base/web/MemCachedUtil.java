package com.base.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.base.util.Constant;
import com.base.util.SpringContextUtil;
import com.danga.MemCached.MemCachedClient;

import biz.entity.company.Company;
import biz.entity.dict.DataDictionary;
import biz.page.company.CompanyPage;
import biz.page.dict.DataDictionaryPage;
import biz.service.company.CompanyService;
import biz.service.dict.DataDictionaryService;

public class MemCachedUtil {
	private static MemCachedUtil instance;
//	private static Object localDataDict = null;
//	private static Object localCompany = null;	
	private static final Log log = LogFactory.getLog(MemCachedUtil.class);
	private static final Hashtable<String, Object[]> TBL_KEY_ALLOCATE = new Hashtable<String, Object[]>();
	private static final int TBL_KEY_ALLOCATE_SIZE = 100; //主键分配大小(需为100整数)

	// @Autowired(required = false)
	private MemCachedClient memCachedClient = (MemCachedClient) SpringContextUtil.getBean("memCachedClient");

	// @Autowired
	private DataDictionaryService<DataDictionary> dataDictionaryService = (DataDictionaryService<DataDictionary>) SpringContextUtil
			.getBean("dataDictionaryService"); 
	

	// @Autowired
	private CompanyService<Company> companyService = (CompanyService<Company>) SpringContextUtil
			.getBean("companyService"); 

	public static MemCachedUtil getInstance() {
		if (instance == null) {
			instance = new MemCachedUtil();
		}
		return instance;
	}

	public Object get(String key) {
		return memCachedClient.get(key);
	}

	public void set(String key, Object obj) {
		memCachedClient.set(key, obj);
	}

	/**
	 * 从MEMORY cache获取数据字典信息Map<String, List<DataDictionary>>
	 * 
	 * @return
	 */
	public Map<String, List<DataDictionary>> getDataDictionaryByTypeFromCache() {
		Object obj = memCachedClient.get(Constant.CACHE_DATADICT);
//		if (obj == null) {
//			obj = localDataDict;
//		}
		if (obj == null || ((Map) obj) == null) {
			// 不存在，则从数据库加载数据
			List<DataDictionary> lst = null;

			HashMap<String, List<DataDictionary>> ciMap = new HashMap<String, List<DataDictionary>>();
			try {
				DataDictionaryPage page = new DataDictionaryPage();
				page.setPagination(false);
				page.setSort("type,nameNumber");
				lst = this.dataDictionaryService.queryByList(page);
				for (DataDictionary sf : lst) {
					ArrayList<DataDictionary> m;
					if (ciMap.containsKey(sf.getType())) {
						m = (ArrayList<DataDictionary>) ciMap.get(sf.getType());
					} else {
						m = new ArrayList<DataDictionary>();
					}
					m.add(sf);
					ciMap.put(sf.getType(), m);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			memCachedClient.set(Constant.CACHE_DATADICT, ciMap);
//			localDataDict = ciMap;
			return ciMap;
		} else {
			log.info("从缓存中获取数据字典消息");
			return (Map) ((Map) obj);
		}
	}

	/**
	 * 从MEMORY cache获取数据字典信息 Map<Lang,Map<Type,Map<code,name>>
	 * 
	 * @return
	 */
	public Map<String, Map<String, String>> getDataDictionaryMapByTypeFromCache() {
		Map<String, List<DataDictionary>> result = this.getDataDictionaryByTypeFromCache();
		Map<String, Map<String, String>> returnMap = new HashMap<String, Map<String, String>>();
		Set<Entry<String, List<DataDictionary>>> entries = result.entrySet();
		for (Entry e : entries) {
			List<DataDictionary> dl = (List<DataDictionary>) e.getValue();
			Map<String, String> tmp = new HashMap<String, String>();
			for (DataDictionary d : dl) {
				tmp.put(d.getCode(), d.getName());
			}
			returnMap.put(e.getKey().toString(), tmp);
		}
		return returnMap;
	}

	/**
	 * 刷新缓存数据字典数据
	 */
	public void refreshDataDictCache() {
//		this.localDataDict = null;
		// 加载系统消息多语言标签
		if (memCachedClient.delete(Constant.CACHE_DATADICT)) {
			this.getDataDictionaryByTypeFromCache();
		}
	}
	
	/**
	 * 从MEMORY cache获取公司信息Company
	 * 
	 * @return
	 */
	public Object getCompanyFromCache() {
		Object obj = memCachedClient.get(Constant.CACHE_COMPANY);
//		if (obj == null) {
//			obj = localCompany;
//		}
		if (obj == null) {
			// 不存在，则从数据库加载数据
			Company comp = null;

			try {
				comp = this.companyService.queryById("A1000");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			memCachedClient.set(Constant.CACHE_COMPANY, comp);
//			localCompany = comp;
			return comp;
		} else {
			log.info("从缓存中获取公司消息");
			return obj;
		}
	}
	/**
	 * 刷新缓存公司信息数据
	 */
	public void refreshCompanyCache() {
//		this.localCompany = null;
		// 加载系统消息多语言标签
		if (memCachedClient.delete(Constant.CACHE_COMPANY)) {
			this.getCompanyFromCache();
		}
	}
	
	/**
	 * 根据表名生成表主键
	 * 
	 * @param tableName
	 * @return
	 */
	public synchronized String generateTblKey(String tableName) {
		String tableNameLocal = tableName.toUpperCase();
		Object obj = memCachedClient.get(Constant.CACHE_TBL_KEY_MAP);
		if (obj == null) {
			//使用本机VM内存数据
			obj = TBL_KEY_ALLOCATE;
		}else{
			//使用CACHE内数据
			TBL_KEY_ALLOCATE.clear();
			TBL_KEY_ALLOCATE.putAll((Hashtable) obj);
		}
		
		Integer currentKeyNbr = null;
		String prefix = null;
		if (TBL_KEY_ALLOCATE.get(tableNameLocal) != null) {
			Object[] keyObj = TBL_KEY_ALLOCATE.get(tableNameLocal);
			keyObj[1] = ((Integer)keyObj[1]).intValue() + 1;
			
			if (((Integer)keyObj[1]).intValue() % TBL_KEY_ALLOCATE_SIZE == 0){
				//重新分配主键
				allocateNewKeyRange(tableNameLocal);
				//重新生成
				return generateTblKey(tableNameLocal);
			}
			
			TBL_KEY_ALLOCATE.put(tableNameLocal, keyObj);
			currentKeyNbr = ((Integer)keyObj[1]);
			prefix = (String)keyObj[0];

			//更新回CACHE
			memCachedClient.set(Constant.CACHE_TBL_KEY_MAP, TBL_KEY_ALLOCATE);
			
			log.debug("Allocate new Key: " +  prefix + currentKeyNbr.intValue() + " for " + tableNameLocal);
			
			// 返回递增主键
			return prefix + currentKeyNbr.intValue();
		} else {
			//重新分配主键
			allocateNewKeyRange(tableNameLocal);

			//更新回CACHE
			memCachedClient.set(Constant.CACHE_TBL_KEY_MAP, TBL_KEY_ALLOCATE);
			
			//重新生成
			return generateTblKey(tableNameLocal);
		}
	}

	/**
	 * 分配新主键区间
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	private synchronized boolean allocateNewKeyRange(String tableName) {
		log.debug("Allocate new key arang...");
		ResourceBundle bundle = ResourceBundle.getBundle("jeecg/jeecg_database");
		String updateSql = "update tbl_key_map set key_prefix=?, key_nbr=? where tbl_name=?";
		String insertSql = "insert into tbl_key_map(tbl_name,key_prefix,key_nbr) values(?,?,?)";
		String selectSql = "select key_prefix,key_nbr from tbl_key_map where tbl_name=?";
		
		//初始数据
		String tblName = tableName.toUpperCase();
		String newKeyPrefix = "A";
		int newKeyNbr = 1000;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"), bundle.getString("password"));
			ps = conn.prepareStatement(selectSql);
			ps.setString(1, tblName);
			rs = ps.executeQuery();
			boolean isDefined = false;
			while (rs.next()) {
				newKeyPrefix = rs.getString(1);
				newKeyNbr = rs.getInt(2);
				isDefined = true;
				
				//检查是否需要更换前缀(每1000更换一次前缀)
				if (newKeyNbr > 1000 && newKeyNbr % 1000 == 0){
					//若前缀未已经轮询至Z，则不更换，继续进入下一千分位
					char c = newKeyPrefix.charAt(0);
					if (c == 90){//当前为Z
						c = 'A'; //重置为A 数字序列不变
					}else{
						c = (char)((int)c + 1);//下一位字母
						//数字回退1000
						newKeyNbr = newKeyNbr-1000;
					}
					//将char转换为字串
					newKeyPrefix = String.valueOf(c);
				}
			}			
			
			if (!isDefined){//若没定义，则需要重新定义
				ps.clearParameters();
				ps = conn.prepareStatement(insertSql);
				ps.setString(1, tblName);
				ps.setString(2, newKeyPrefix);
				ps.setInt(3, newKeyNbr + TBL_KEY_ALLOCATE_SIZE);
				ps.execute();
			}else{//已定义，则分配新区间
				ps.clearParameters();
				ps = conn.prepareStatement(updateSql);
				ps.setString(1, newKeyPrefix);
				ps.setInt(2, newKeyNbr + TBL_KEY_ALLOCATE_SIZE);
				ps.setString(3, tblName);
				ps.execute();
			}
			
			//更新主键池
			TBL_KEY_ALLOCATE.put(tblName, new Object[]{newKeyPrefix, newKeyNbr});
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return true;
	}
	
	public static void main(String[] args){
		int j = 65;
		char c = (char)j;
		System.out.println(c);
		
		char b = 'Z';
		int k = (int)b;
		System.out.println(k);
//		System.out.println(1100%1000);
		for (int i = 0; i < 4; i++){
			System.out.println(MemCachedUtil.getInstance().generateTblKey("PERFORMING"));
		}
	}
	
}
