package com.inclination.scaffold.application.menu;

import com.inclination.scaffold.api.request.menu.MenuQryByPage;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

public interface MenuService {

	void addMenu(MenuDto map) throws TException;

	void modifyMenu(MenuDto map) throws TException;

	void deleteMenu(MenuDto dto) throws TException;

	ViewData findMenu(MenuQryByPage request);

	ViewData findMenusByRoid(Integer roid);

	ViewData menuBatchRemove(String ids) throws TException;
}
