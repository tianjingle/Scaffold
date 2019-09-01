package com.inclination.scaffold.api.response.toolproject;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ToolProjectQryAllResponse {

	@ApiModelProperty(name = "list", value = "所有的工具信息")
	private List<ToolProjectResponse> list;

	public List<ToolProjectResponse> getList() {
		return list;
	}

	public void setList(List<ToolProjectResponse> list) {
		this.list = list;
	}
	
	
}
