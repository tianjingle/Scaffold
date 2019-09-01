package com.inclination.scaffold.api.response.rolemenu;

import io.swagger.annotations.ApiModelProperty;

public class RoleMenuManagerResponse {

	@ApiModelProperty(name="id",notes="id")
	private Integer id;

	@ApiModelProperty(name="roid",notes="权限id")
	private Integer roid;

	@ApiModelProperty(name="tmid",notes="菜单id")
	private Integer tmid;
	
	@ApiModelProperty(name="rolename",notes="角色名称")
	private String rolename;
	
	@ApiModelProperty(name="menuname",notes="菜单名称")
	private String menuname;

	
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
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
