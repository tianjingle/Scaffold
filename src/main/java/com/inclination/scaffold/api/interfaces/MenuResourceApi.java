package com.inclination.scaffold.api.interfaces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.menuresource.MenuResourceAddRequest;
import com.inclination.scaffold.api.response.resource.ResourceManagerAllResponse;
import com.inclination.scaffold.application.menuresource.MenuResourceDto;
import com.inclination.scaffold.application.menuresource.MenuResourceService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 资源与菜单资源之间的多对多关系（中间表）
 * @author tianjingle 2019.6.7 all right protect.
 *
 */
@Api
@RestController
public class MenuResourceApi {

	/**
	 * 注入数据库服务
	 */
	@Autowired
	private MenuResourceService menuResourceService;
	/**
	 * 资源与菜单资源之间关系的添加
	 * @param request
	 * @throws TException 
	 */
	@PostMapping(value="/menus-resources-manager")
	@ApiOperation(value="菜单资源新增",notes="菜单资源新增")
	public void addMenuResource(@RequestBody MenuResourceAddRequest[] request) throws TException{
		for(int i=0;i<request.length;i++){
			MenuResourceDto dto=ModelMapUtils.map(request[i], MenuResourceDto.class);
			menuResourceService.menuResourceAdd(dto);
		}
	}

	/**
	 * 资源菜单资源之间关系的修改
	 * @param request 关系实体
	 * @throws TException
	 */
/*	@PatchMapping(value="/menus-resources-manager")
	public void modifyMenuResource(@RequestBody MenuResourceModifyRequest request) throws TException{
		MenuResourceDto dto=ModelMapUtils.map(request, MenuResourceDto.class);
		menuResourceService.menuResourceModify(dto);
	}*/
	
	/**
	 * 资源菜单资源之间关系的删除
	 * @param id 删除的主键
	 * @throws TException
	 */
	@DeleteMapping(value="/menus-resources-manager/{id}")
	@ApiOperation(value="菜单资源删除",notes="菜单资源删除")
	public void deleteMenuResource(@PathVariable String id) throws TException{
		MenuResourceDto dto=new MenuResourceDto();
		dto.setId(Integer.parseInt(id));
		menuResourceService.menuResourceDelete(dto);
	}
	
	@GetMapping(value="/menus-resources-manager/{menuid}")
	@ApiOperation(value="菜单资源查询",notes="菜单资源查询")
	public ResourceManagerAllResponse findAllMenuResources(@PathVariable String menuid){
		return menuResourceService.menuResourceFindsNew(Integer.parseInt(menuid));
	}
/*	@GetMapping(value="/menus-resources-manager-old")
	public MenuResourceQryResponse findAllMenuResources(){
		return menuResourceService.menuResourceFinds();
	}*/
}
