package com.inclination.scaffold.application.rolemenu;

import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerAllResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerQryResponse;
import com.inclination.scaffold.constant.exception.TException;

public interface RoleMenuService {

	void roleMenuAdd(RoleMenuDto dto) throws TException;

	void roleMenuModify(RoleMenuDto dto) throws TException;

	void roleMenuDelete(RoleMenuDto dto) throws TException;

	RoleMenuManagerQryResponse roleMenuFind();

	RoleMenuManagerAllResponse roleMenuFindNew(Integer id);

}
