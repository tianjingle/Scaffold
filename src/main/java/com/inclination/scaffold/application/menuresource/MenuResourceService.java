package com.inclination.scaffold.application.menuresource;

import com.inclination.scaffold.api.request.menuresource.MenuResourceAddRequest;
import com.inclination.scaffold.api.response.menuresource.MenuResourceQryResponse;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

public interface MenuResourceService {

	/**
	 * 菜单与资源之间的关系添加
	 *
	 * @param id
	 * @param dto
	 * @throws TException
	 */
	void menuResourceAdd(String id, MenuResourceAddRequest[] dto) throws TException;

	/**
	 * 菜单资源之间关系的修改
	 * @param dto 新的资源菜单关系
	 * @throws TException 
	 */
	void menuResourceModify(MenuResourceDto dto) throws TException;

	/**
	 * 资源菜单之间的删除
	 * @param dto 封装的资源菜单关系id
	 * @throws TException 
	 */
	void menuResourceDelete(MenuResourceDto dto) throws TException;

	/**
	 * @return 结果集合
	 * 
	 */
	MenuResourceQryResponse menuResourceFinds();

	/**
	 * 返回查询的结果集合
	 * @param menuid
	 * @return
	 */
	ViewData menuResourceFindsNew(Integer menuid);

}
