package com.inclination.scaffold.api.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.rolemenu.RoleMenuAddRequest;
import com.inclination.scaffold.api.request.rolemenu.RoleMenuModifyRequest;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerAllResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerQryResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerResponse;
import com.inclination.scaffold.application.rolemenu.RoleMenuDto;
import com.inclination.scaffold.application.rolemenu.RoleMenuService;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

/**
 * @desc this class manager role-menu`s relationship.
 * @version 1.0
 * @author tianjingle All right protected time:2019:6:7
 *
 */
@RestController
@ApiModel
public class RoleMenuManagerApi {

	/**
	 * 注入application服务
	 */
	@Autowired
	private RoleMenuService roleMenuService;
	
	/**
	 * 角色-菜单的添加
	 * @param request 添加的内容
	 * @throws TException
	 */
/*	@PostMapping(value="/roles-menus-manager")
	public void addRoleMenus(@RequestBody RoleMenuAddRequest request) throws TException{
		RoleMenuDto dto=ModelMapUtils.map(request, RoleMenuDto.class);
		roleMenuService.roleMenuAdd(dto);
	}*/
	/**
	 * 角色菜单修改
	 * @param request
	 * @throws TException
	 */
	@PatchMapping(value="/roles-menus-manager")
	@ApiOperation(value="角色资源修改",notes="角色资源修改")
	public void modifyRoleMenus(@RequestBody RoleMenuModifyRequest[] request) throws TException{
		for(int i=0;i<request.length;i++){
			RoleMenuDto dto=ModelMapUtils.map(request[i], RoleMenuDto.class);
			roleMenuService.roleMenuModify(dto);
		}
	}
	/**
	 * 角色菜单删除
	 * @param id
	 * @throws TException
	 */
	@DeleteMapping(value="/roles-menus-manager/{id}")
	@ApiOperation(value="角色资源删除",notes="角色资源删除")
	public void deleteRoleMenus(@PathVariable Integer id) throws TException{
		RoleMenuDto dto=new RoleMenuDto();
		dto.setId(id);
		roleMenuService.roleMenuDelete(dto);
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping(value="/roles-menus-manager/{id}")
	@ApiOperation(value="角色资源查询",notes="角色资源查询")
	public RoleMenuManagerAllResponse findAllRoleMenuNew(@PathVariable Integer id){
		return roleMenuService.roleMenuFindNew(id);
	}
/*	
	@GetMapping(value="/roles-menus-manager-old")
	public RoleMenuManagerQryResponse findAllRoleMenu(){
		return roleMenuService.roleMenuFind();
	}*/
}
