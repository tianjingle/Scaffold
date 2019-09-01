package com.inclination.scaffold.application.role;

import com.inclination.scaffold.api.request.role.RoleQryByPage;
import com.inclination.scaffold.api.response.role.RoleManageAllResponse;
import com.inclination.scaffold.api.response.role.RoleManageQryResponse;
import com.inclination.scaffold.constant.config.TException;

public interface RoleService {

	void roleAdd(RoleDto dto) throws TException;

	void modifyRole(RoleDto dto) throws TException;

	void deleteRole(RoleDto dto) throws TException;

	RoleManageQryResponse findAllByPage(RoleQryByPage request);

	RoleManageAllResponse findAllRole();

}
