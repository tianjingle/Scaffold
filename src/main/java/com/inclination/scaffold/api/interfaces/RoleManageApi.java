package com.inclination.scaffold.api.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.role.RoleManageAddRequest;
import com.inclination.scaffold.api.request.role.RoleManageModifyRequest;
import com.inclination.scaffold.api.request.role.RoleQryByPage;
import com.inclination.scaffold.api.response.role.RoleManageAllResponse;
import com.inclination.scaffold.api.response.role.RoleManageQryResponse;
import com.inclination.scaffold.application.role.RoleDto;
import com.inclination.scaffold.application.role.RoleService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

/**
 * @desc this class manager role.
 * @version 1.0
 * @author tianjingle All right protected time:2019:6:2
 *
 */
@RestController
@ApiModel
public class RoleManageApi {

	/**
	 * 注入权限管理
	 */
	@Autowired
	private RoleService roleService;
	/**
	 * 权限的新增
	 * @param request 新增的权限属性
	 * @throws TException 
	 */
	@PostMapping(value="/role-manager",consumes="application/json",produces = "application/json;charset=utf-8")
	@ApiOperation(value="角色添加",notes="角色添加")
	public void roleAdd(@RequestBody RoleManageAddRequest request) throws TException{
		RoleDto dto=ModelMapUtils.map(request, RoleDto.class);
		roleService.roleAdd(dto);
	}
	/**
	 * 
	 * 角色的修改
	 * @param request 修改之后的角色
	 * @throws TException 
	 */
	@PatchMapping(value="/role-manager",consumes="application/json",produces="application/json;charset=utf-8")
	@ApiOperation(value="角色修改",notes="角色修改")
	public void roleModify(@RequestBody RoleManageModifyRequest request) throws TException{
		RoleDto dto=ModelMapUtils.map(request, RoleDto.class);
		roleService.modifyRole(dto);
	}
	/**
	 * 删除角色
	 * @param id 角色的id
	 * @throws TException 
	 */
	@DeleteMapping(value="/role-manager/{id}",consumes="application/json",produces="application/json;charset=utf-8")
	@ApiOperation(value="角色删除",notes="角色删除")
	public void roleDelete(@PathVariable String id) throws TException{
		RoleDto dto=new RoleDto();
		dto.setId(Integer.parseInt(id));
		roleService.deleteRole(dto);
	}
	/**
	 * 模糊查询角色信息
	 * @param request 模糊查询结果
	 */
	@GetMapping(value="/role-manager")
	@ApiOperation(value="角色查询",notes="角色查询")
	public RoleManageQryResponse findRole(@ModelAttribute RoleQryByPage request){
		return roleService.findAllByPage(request);
	}
	
	@GetMapping(value="/role-manager-all")
	@ApiOperation(value="所有角色查询",notes="所有角色查询")
	public RoleManageAllResponse findAllRole(){
		return roleService.findAllRole();
	}
}
