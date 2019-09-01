package com.inclination.scaffold.api.request.menuresource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MenuResourceModifyRequest {

	@ApiModelProperty(name = "id", notes = "id")
	private Integer id;
	
	@ApiModelProperty(name = "menuid", notes = "菜单id")
	private Integer menusid;

	@ApiModelProperty(name = "resourceid", notes = "资源id")
	private Integer resourcesid;

	@ApiModelProperty(name="flag",notes="有无标志")
	private String flag;

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
