package com.inclination.scaffold.api.response.role;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class RoleManageAllResponse {

	@ApiModelProperty(name="list",value="查询结果")
	private List<RoleManageResponse> list;


	public List<RoleManageResponse> getList() {
		return list;
	}

	public void setList(List<RoleManageResponse> list) {
		this.list = list;
	}
}
