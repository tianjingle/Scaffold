package com.inclination.scaffold.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerAllResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerResponse;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.MenuPoMapper;
import com.inclination.scaffold.infrastraction.repository.RoleMenuPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.*;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import tk.mybatis.mapper.entity.Example;

public class DRoleMenu {
	
	private Integer id;

	private Integer roleId;

	private Integer menuId;

	private String flag;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public String getFlag() {
		return flag;
	}

	/**
	 * 角色资源的插入
	 * @param roleMenuMapper
	 * @throws TException
	 */
	public void addRoleMenu(RoleMenuPoMapper roleMenuMapper) throws TException {
		// TODO Auto-generated method stub
		RoleMenuPo po=ModelMapUtils.map(this, RoleMenuPo.class);
		Example example=new Example(RoleMenuPo.class);
		Example.Criteria criteria=example.createCriteria()
				.andEqualTo("roleId",po.getRoleId())
				.andEqualTo("menuId",po.getMenuId());
		if(roleMenuMapper.selectCountByExample(po)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLEMENU_CODE,TErrorCode.ERROR_EXISIT_ROLEMENU_MSG);
		}else if(roleMenuMapper.insert(po)!=1){
		    throw new TException(TErrorCode.ERROR_INSERT_ROLEMENU_CODE,TErrorCode.ERROR_INSERT_ROLEMENU_MSG);
		}
	}
	/**
	 * 角色菜单修改
	 * @param roleMenuMapper
	 * @throws TException
	 */
	public void modifyRoleMenuNew(RoleMenuPoMapper roleMenuMapper) throws TException {
		// TODO Auto-generated method stub
		RoleMenuPo po=ModelMapUtils.map(this, RoleMenuPo.class);
		roleMenuMapper.insert(po);
	}

	public void deleteRoleMenu(RoleMenuPoMapper roleMenuMapper) throws TException {
		// TODO Auto-generated method stub
		if(roleMenuMapper.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_ROLEMENU_CODE,TErrorCode.ERROR_DELETE_ROLEMENU_MSG);
		}
	}

	public ViewData findMyMenuWithFlag(MenuPoMapper menuMapping, RoleMenuPoMapper roleMenuMapper) {
		// TODO Auto-generated method stub
		List<MenuPo> menulist=menuMapping.selectAll();
		Map<String,Object> map=new HashMap<>();
		Example example=new Example(RoleMenuPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("roleId",this.getRoleId());
		List<RoleMenuPo> roleMenuList=roleMenuMapper.selectByExample(example);
		for(int i=0;i<roleMenuList.size();i++){
			map.put(String.valueOf(roleMenuList.get(i).getMenuId()), roleMenuList.get(i));
		}
		for(int i=0;i<menulist.size();i++){
			if(map.get(String.valueOf(menulist.get(i).getId()))!=null){
				menulist.get(i).setFlag("Y");
			}else{
				menulist.get(i).setFlag("N");
			}
		}
		return ViewData.success(ModelMapUtils.map(menulist, RoleMenuManagerResponse.class));
	}

}
