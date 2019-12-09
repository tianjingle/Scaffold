package com.inclination.scaffold.application.role;

import com.inclination.scaffold.api.request.role.RoleQryByPage;
import com.inclination.scaffold.api.response.role.RoleManageAllResponse;
import com.inclination.scaffold.api.response.role.RoleManageQryResponse;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

import javax.swing.text.View;

public interface RoleService {

	void roleAdd(RoleDto dto) throws TException;

	void modifyRole(RoleDto dto) throws TException;

	void deleteRole(RoleDto dto) throws TException;

	ViewData findAllByPage(RoleQryByPage request);

	ViewData findAllRole();

    ViewData batchRemove(String ids) throws TException;
}
