package com.jeecg.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.base.util.Constant;
import com.jeecg.dao.SysUserDao;
import com.jeecg.entity.SysRoleRel;
import com.jeecg.page.SysUserModel;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserService<br>
 * 
 * <b>日期：</b> July, 2015 <br>
 * 
 */
@Service("sysUserService")
public class SysUserService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SysUserService.class);

	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		for (Object id : ids) {
			sysRoleRelService.deleteByObjId((String) id, Constant.RelType.USER.key);
		}
	}

	/**
	 * 检查登录
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(String email, String pwd) {
		SysUserModel model = new SysUserModel();
		model.setEmail(email);
		model.setPwd(pwd);
		SysUserDao<T> mapper = getDao();
		mapper.queryById("");
		return getDao().queryLogin(model);
	}

	/**
	 * 查询邮箱总数，检查是否存在
	 * 
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email) {
		return getDao().getUserCountByEmail(email);
	}

	/**
	 * 查询用户权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysRoleRel> getUserRole(String userId) {
		return sysRoleRelService.queryByObjId(userId, Constant.RelType.USER.key);
	}

	/**
	 * 添加用户权限
	 * 
	 * @param userId
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRole(String userId, String[] roleIds) throws Exception {
		if (userId == null || roleIds == null || roleIds.length < 1) {
			return;
		}
		// 清除关联关系
		sysRoleRelService.deleteByObjId(userId, Constant.RelType.USER.key);
		List<SysRoleRel> rels = new ArrayList<SysRoleRel>();
		for (String roleId : roleIds) {
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(userId);
			rel.setRelType(Constant.RelType.USER.key);
			rels.add(rel);
		}
		sysRoleRelService.addBatch(rels);
	}

	@Autowired
	private SysUserDao<T> mapper;

	public SysUserDao<T> getDao() {
		return mapper;
	}

}
