package com.inclination.scaffold.application;


import com.inclination.scaffold.infrastraction.repository.MenuPoMapper;
import com.inclination.scaffold.infrastraction.repository.RoleMenuPoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerAllResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerQryResponse;
import com.inclination.scaffold.application.rolemenu.RoleMenuDto;
import com.inclination.scaffold.application.rolemenu.RoleMenuService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DRoleMenu;
import com.inclination.scaffold.utils.ModelMapUtils;

@Service
public class RoleMenuServiceImp implements RoleMenuService{

	/**
	 * 注入数据库服务
	 */
	@Autowired
	private RoleMenuPoMapper roleMenuMapper;

	/**
	 * 菜单的数据库服务
	 */
	@Autowired
	private MenuPoMapper menuMapping;
	
	
	@Override
	@Transactional
	public void roleMenuAdd(RoleMenuDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DRoleMenu.class).addRoleMenu(roleMenuMapper);
	}

	@Override
	@Transactional
	public void roleMenuModify(RoleMenuDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DRoleMenu.class).modifyRoleMenu(roleMenuMapper);
	}

	@Override
	@Transactional
	public void roleMenuDelete(RoleMenuDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DRoleMenu.class).deleteRoleMenu(roleMenuMapper);
	}

	@Override
	public RoleMenuManagerQryResponse roleMenuFind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleMenuManagerAllResponse roleMenuFindNew(Integer id) {
		// TODO Auto-generated method stub
		DRoleMenu domain=new DRoleMenu();
		domain.setRoleId(id);
		return domain.findMyMenuWithFlag(menuMapping,roleMenuMapper);
	}
	
	
}
