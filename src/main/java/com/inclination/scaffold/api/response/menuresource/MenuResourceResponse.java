package com.inclination.scaffold.api.response.menuresource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MenuResourceResponse {

	@ApiModelProperty(name = "id", notes = "id")
	private Integer id;
	
	@ApiModelProperty(name = "menuid", notes = "菜单id")
	private Integer menusid;

	@ApiModelProperty(name = "resourceid", notes = "资源id")
	private Integer resourcesid;

	@ApiModelProperty(name = "menuname", notes = "菜单名称")
	private String menuname;

	@ApiModelProperty(name = "resourcename", notes = "资源名称")
	private String resourcename;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenusid() {
		return menusid;
	}

	public void setMenusid(Integer menusid) {
		this.menusid = menusid;
	}

	public Integer getResourcesid() {
		return resourcesid;
	}

	public void setResourcesid(Integer resourcesid) {
		this.resourcesid = resourcesid;
	}
}
