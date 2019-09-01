package com.inclination.scaffold.application.menu;

import com.inclination.scaffold.api.request.menu.MenuQryByPage;
import com.inclination.scaffold.api.response.menu.MenuManagerQryResponse;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.utils.ViewData;

public interface MenuService {

	void addMenu(MenuDto map) throws TException;

	void modifyMenu(MenuDto map) throws TException;

	void deleteMenu(MenuDto dto) throws TException;

	MenuManagerQryResponse findMenu(MenuQryByPage request);

	ViewData findMenusByRoid(Integer roid);

}
