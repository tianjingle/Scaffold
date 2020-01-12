package com.inclination.scaffold.api.interfaces;

import com.google.common.base.Strings;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	@PatchMapping(value = "/menu-manager")
	@ApiOperation(value="菜单修改",notes="菜单修改")
	public void menuModify(@RequestBody MenuModifyRequest request) throws TException {
		menuService.modifyMenu(ModelMapUtils.map(request, MenuDto.class));
	}
	/**
	 * 菜单的删除
	 * @param id 菜单的id
	 * @throws TException 抛出异常
	 */
	@DeleteMapping(value="/menu-manager/{id}")
	@ApiOperation(value="菜单删除",notes="菜单删除")
	public void menuDelete(@PathVariable String id) throws TException{
		MenuDto dto=new MenuDto();
		dto.setId(Integer.parseInt(id));
		menuService.deleteMenu(dto);
	}

	@DeleteMapping(value = "/menu-batch-remove")
	@ApiOperation(value = "菜单批量删除")
	public ViewData menuBatchRemove(String ids) throws TException {
		if(Strings.isNullOrEmpty(ids)){
			throw new TException(TErrorCode.ERROR_DELETE_MENU_CODE,TErrorCode.ERROR_DELETE_MENU_MSG);
		}
		return menuService.menuBatchRemove(ids);
	}
	/**
	 * 菜单的查询
	 * @param request 菜单查询的条件
	 * @return 返回查询的结果
	 */
	@GetMapping(value="/menu-manager")
	@ApiOperation(value="菜单查询",notes="菜单查询")
	public ViewData findMenuByPages(@ModelAttribute MenuQryByPage request){
		return menuService.findMenu(request);
	}

	/**
	 * 查询用户的访问菜单列表
	 * @return 返回结果集
	 */
	@GetMapping(value="/user-menus")
	@ApiOperation(value="用户的访问菜单列表",notes="用户访问的菜单列表")
	public ViewData findUserMenu(@SessionAttribute("CurrentUser")UserDto user) throws TException {
		if(user==null){
			throw new TException(TErrorCode.ERROR_TIME_OUT_CODE,TErrorCode.ERROR_TIME_OUT_MSG);
		}
		return menuService.findMenusByRoid(user.getRoId());
	}
}
