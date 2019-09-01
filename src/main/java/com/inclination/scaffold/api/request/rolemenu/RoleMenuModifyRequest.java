package com.inclination.scaffold.api.request.rolemenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleMenuModifyRequest {
	@ApiModelProperty(name="id",notes="id")
	private Integer id;

	@ApiModelProperty(name="roid",notes="权限id")
	private Integer roid;

	@ApiModelProperty(name="tmid",notes="菜单id")
	private Integer tmid;

	@ApiModelProperty(name="flag",notes="有无标志")
	private String flag;
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
