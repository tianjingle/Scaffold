package com.inclination.scaffold.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.api.request.role.RoleQryByPage;
import com.inclination.scaffold.api.response.role.RoleManageAllResponse;
import com.inclination.scaffold.api.response.role.RoleManageQryResponse;
import com.inclination.scaffold.api.response.role.RoleManageResponse;
import com.inclination.scaffold.api.response.user.UserManageResponse;
import com.inclination.scaffold.api.response.user.UserManagerQryResponse;
import com.inclination.scaffold.application.role.RoleDto;
import com.inclination.scaffold.application.role.RoleService;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.domain.DRole;
import com.inclination.scaffold.infrastraction.repository.RoleMapper;
import com.inclination.scaffold.infrastraction.repository.po.Role;
import com.inclination.scaffold.infrastraction.repository.po.RoleExample;
import com.inclination.scaffold.utils.ModelMapUtils;

@Service
public class RoleServiceImp implements RoleService {

	/**
	 * 注入操作数据库
	 */
	@Autowired
	private RoleMapper roleMapping;
	
	@Override
	@Transactional
	public void roleAdd(RoleDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DRole.class).addRole(roleMapping);
		
	}

	@Override
	@Transactional
	public void modifyRole(RoleDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DRole.class).modifyRole(roleMapping);
	}

	@Override
	@Transactional
	public void deleteRole(RoleDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DRole.class).deleteRole(roleMapping);
	}

	@Override
	public RoleManageQryResponse findAllByPage(RoleQryByPage request) {
		// TODO Auto-generated method stub
		Role po=ModelMapUtils.map(request, Role.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<Role> list=roleMapping.selectBySelective(po);
		RoleManageQryResponse response = new RoleManageQryResponse();
		response.setList(ModelMapUtils.map(list, RoleManageResponse.class));
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return response;
	}

	@Override
	public RoleManageAllResponse findAllRole() {
		// TODO Auto-generated method stub
		List<Role> list=roleMapping.selectBySelective(new Role());
		RoleManageAllResponse response=new RoleManageAllResponse();
		response.setList(ModelMapUtils.map(list, RoleManageResponse.class));
		return response;
	}

}
