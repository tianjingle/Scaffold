package com.inclination.scaffold.application;

import java.util.Arrays;
import java.util.List;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.infrastraction.repository.RolePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.RolePo;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.api.request.role.RoleQryByPage;
import com.inclination.scaffold.api.response.role.RoleManageAllResponse;
import com.inclination.scaffold.api.response.role.RoleManageQryResponse;
import com.inclination.scaffold.api.response.role.RoleManageResponse;
import com.inclination.scaffold.application.role.RoleDto;
import com.inclination.scaffold.application.role.RoleService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DRole;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class RoleServiceImpl implements RoleService {

	/**
	 * 注入操作数据库
	 */
	@Autowired
	private RolePoMapper roleMapping;
	
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
	public ViewData findAllByPage(RoleQryByPage request) {
		// TODO Auto-generated method stub
		RolePo po=ModelMapUtils.map(request, RolePo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<RolePo> list=roleMapping.selectBySelective(po);
		return ViewData.success(ModelMapUtils.map(list, RoleManageResponse.class),hpage.getPages(),hpage.getTotal());
	}

	@Override
	public RoleManageAllResponse findAllRole() {
		// TODO Auto-generated method stub
		List<RolePo> list=roleMapping.selectAll();
		RoleManageAllResponse response=new RoleManageAllResponse();
		response.setList(ModelMapUtils.map(list, RoleManageResponse.class));
		return response;
	}

	@Override
	public ViewData batchRemove(String ids) throws TException {
		Example example=new Example(RolePo.class);
		example.createCriteria().andIn("id", Arrays.asList(ids.split(",")));
		if(roleMapping.deleteByExample(example)<1){
			throw new TException(TErrorCode.ERROR_DELETE_ROLE_CODE,TErrorCode.ERROR_DELETE_ROLE_MSG);
		}
		return ViewData.success(true);
	}

}
