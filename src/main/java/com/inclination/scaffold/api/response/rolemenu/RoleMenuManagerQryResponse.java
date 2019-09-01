package com.inclination.scaffold.api.response.rolemenu;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleMenuManagerQryResponse {

	@ApiModelProperty(name="list",notes="结果集合")
	private List<RoleMenuManagerResponse> list;

	public List<RoleMenuManagerResponse> getList() {
		return list;
	}

	public void setList(List<RoleMenuManagerResponse> list) {
		this.list = list;
	}
	
	
}
