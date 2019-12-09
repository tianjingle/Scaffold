package com.inclination.scaffold.application.rolemenu;

import com.inclination.scaffold.api.request.rolemenu.RoleMenuModifyRequest;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerAllResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerQryResponse;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

public interface RoleMenuService {

	void roleMenuAdd(RoleMenuDto dto) throws TException;


	void roleMenuDelete(RoleMenuDto dto) throws TException;

	RoleMenuManagerQryResponse roleMenuFind();

	ViewData roleMenuFindNew(Integer id);

	void roleMenuModify(String id, RoleMenuModifyRequest[] request) throws TException;
}
