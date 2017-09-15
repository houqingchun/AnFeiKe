package com.jeecg.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.base.util.Constant;
import com.jeecg.dao.SysRoleDao;
import com.jeecg.entity.SysRole;
import com.jeecg.entity.SysRoleRel;

/**
 * 
 * <br>
 * <b>功能：</b>SysRoleService<br>
 * 
 * <b>日期：</b> July, 2015 <br>
 * 
 */
@Service("sysRoleService")
public class SysRoleService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SysRoleService.class);

	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;

	/**
	 * 添加角色&菜单关系
	 */
	public void addRoleMenuRel(String roleId, String[] menuIds) throws Exception {
		if (roleId == null || menuIds == null || menuIds.length < 1) {
			return;
		}
		List<SysRoleRel> rels = new ArrayList<SysRoleRel>();
		for (String menuid : menuIds) {
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(menuid);
			rel.setRelType(Constant.RelType.MENU.key);
			rels.add(rel);
		}
		sysRoleRelService.addBatch(rels);
	}

	/**
	 * 添加角色&菜单关系
	 */
	public void addRoleBtnRel(String roleId, String[] btnIds) throws Exception {
		if (roleId == null || btnIds == null || btnIds.length < 1) {
			return;
		}
		List<SysRoleRel> rels = new ArrayList<SysRoleRel>();
		for (String btnid : btnIds) {
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(btnid);
			rel.setRelType(Constant.RelType.BTN.key);
			rels.add(rel);
		}
		sysRoleRelService.addBatch(rels);
	}

	/**
	 * 添加
	 * 
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void add(SysRole role, String[] menuIds, String[] btnIds) throws Exception {
		super.add((T) role);
		addRoleMenuRel(role.getId(), menuIds);
		addRoleBtnRel(role.getId(), btnIds);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(String[] ids) throws Exception {
		super.delete(ids);
		for (String id : ids) {
			// 清除关联关系
			sysRoleRelService.deleteByRoleId(id);
		}
	}

	/**
	 * 修改
	 * 
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void update(SysRole role, String[] menuIds, String[] btnIds) throws Exception {
		super.update((T) role);
		// 如果角色被禁用则删除关联的用户关系
		if (Constant.STATE.DISABLE.key == role.getState()) {
			sysRoleRelService.deleteByRoleId(role.getId(), Constant.RelType.USER.key);
		}
		// 清除关联关系
		sysRoleRelService.deleteByRoleId(role.getId(), Constant.RelType.MENU.key);
		sysRoleRelService.deleteByRoleId(role.getId(), Constant.RelType.BTN.key);
		addRoleMenuRel(role.getId(), menuIds);
		addRoleBtnRel(role.getId(), btnIds);

	}

	/**
	 * 查询全部有效的权限
	 * 
	 * @return
	 */
	public List<T> queryAllList() {
		return getDao().queryAllList();
	}

	/**
	 * 查询全部有效的权限
	 * 
	 * @return
	 */
	public List<T> queryByUserid(String userid) {
		return getDao().queryByUserid(userid);
	}

	@Autowired
	private SysRoleDao<T> mapper;

	public SysRoleDao<T> getDao() {
		return mapper;
	}

}
