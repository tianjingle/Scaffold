package com.inclination.scaffold.api.response.menuresource;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MenuResourceQryResponse {

	@ApiModelProperty(name="list",notes="返回的数据集合")
	private List<MenuResourceResponse> list;

	public List<MenuResourceResponse> getList() {
		return list;
	}

	public void setList(List<MenuResourceResponse> list) {
		this.list = list;
	}
	
	
}
