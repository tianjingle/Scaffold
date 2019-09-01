package com.inclination.scaffold.api.request.rolemenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleMenuAddRequest {
	

	@ApiModelProperty(name="roid",notes="权限id")
	private Integer roid;

	@ApiModelProperty(name="tmid",notes="菜单id")
	private Integer tmid;


	public Integer getRoid() {
		return roid;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}

	public Integer getTmid() {
		return tmid;
	}

	public void setTmid(Integer tmid) {
		this.tmid = tmid;
	}

}
