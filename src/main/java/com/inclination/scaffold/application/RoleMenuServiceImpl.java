package com.inclination.scaffold.application;


import com.inclination.scaffold.api.request.rolemenu.RoleMenuModifyRequest;
import com.inclination.scaffold.infrastraction.repository.MenuPoMapper;
import com.inclination.scaffold.infrastraction.repository.RoleMenuPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.RoleMenuPo;
import com.inclination.scaffold.utils.ViewData;
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
import tk.mybatis.mapper.entity.Example;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{

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
	public ViewData roleMenuFindNew(Integer id) {
		// TODO Auto-generated method stub
		DRoleMenu domain=new DRoleMenu();
		domain.setRoleId(id);
		return domain.findMyMenuWithFlag(menuMapping,roleMenuMapper);
	}

	@Override
	public void roleMenuModify(String id, RoleMenuModifyRequest[] request) throws TException {
		Example example=new Example(RoleMenuPo.class);
		example.createCriteria().andEqualTo("roleId",id);
		roleMenuMapper.deleteByExample(example);
		for(int i=0;i<request.length;i++){
			RoleMenuDto dto=ModelMapUtils.map(request[i], RoleMenuDto.class);
			ModelMapUtils.map(dto, DRoleMenu.class).modifyRoleMenuNew(roleMenuMapper);
		}
	}


}
