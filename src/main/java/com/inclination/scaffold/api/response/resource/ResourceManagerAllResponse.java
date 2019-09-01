package com.inclination.scaffold.api.response.resource;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResourceManagerAllResponse {

	@ApiModelProperty(name="list",notes="返回的结果集")
	private List<ResourceManagerResponse> list;

	public List<ResourceManagerResponse> getList() {
		return list;
	}

	public void setList(List<ResourceManagerResponse> list) {
		this.list = list;
	}
	
}
