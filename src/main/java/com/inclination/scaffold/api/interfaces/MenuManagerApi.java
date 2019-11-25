package com.inclination.scaffold.api.interfaces;

import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.menu.MenuAddRequest;
import com.inclination.scaffold.api.request.menu.MenuModifyRequest;
import com.inclination.scaffold.api.request.menu.MenuQryByPage;
import com.inclination.scaffold.api.response.menu.MenuManagerQryResponse;
import com.inclination.scaffold.application.menu.MenuDto;
import com.inclination.scaffold.application.menu.MenuService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewDataOld;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class MenuManagerApi {
	
	/**
	 * 注入菜单服务
	 */
	@Autowired
	private MenuService menuService;


	/**
	 * 菜单的新增
	 * 
	 * @param reqeust
	 * @throws TException
	 */
	@PostMapping(value = "/menu-manager")
	@ApiOperation(value="菜单新增",notes="菜单新增")
	public void menuAdd(@RequestBody MenuAddRequest reqeust) throws TException {
		menuService.addMenu(ModelMapUtils.map(reqeust, MenuDto.class));
	}

	/**
	 * 菜单的修改
	 * 
	 * @param request 修改之后的菜单
	 * @throws TException 
	 */
	@PatchMapping(value = "/menu-manager", produces = "application/json;charset=utf-8")
	@ApiOperation(value="菜单修改",notes="菜单修改")
	public void menuModify(@ModelAttribute MenuModifyRequest request) throws TException {
		menuService.modifyMenu(ModelMapUtils.map(request, MenuDto.class));
	}
	/**
	 * 菜单的删除
	 * @param id 菜单的id
	 * @throws TException 抛出异常
	 */
	@DeleteMapping(value="/menu-manager/{id}",consumes="application/json",produces="application/json;charset=utf-8")
	@ApiOperation(value="菜单删除",notes="菜单删除")
	public void menuDelete(@PathVariable String id) throws TException{
		MenuDto dto=new MenuDto();
		dto.setId(Integer.parseInt(id));
		menuService.deleteMenu(dto);
	}
	/**
	 * 菜单的查询
	 * @param request 菜单查询的条件
	 * @return 返回查询的结果
	 */
	@GetMapping(value="/menu-manager")
	@ApiOperation(value="菜单查询",notes="菜单查询")
	public MenuManagerQryResponse findMenuByPages(@ModelAttribute MenuQryByPage request){
		return menuService.findMenu(request);
	}
	/**
	 * 查询用户的访问菜单列表
	 * @param roid 权限位
	 * @return 返回结果集
	 */
	@GetMapping(value="/user-menus/{roid}")
	@ApiOperation(value="用户的访问菜单列表",notes="用户访问的菜单列表")
	public ViewData findUserMenu(@PathVariable Integer roid){
		return menuService.findMenusByRoid(roid);
	}
}
