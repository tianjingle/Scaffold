package com.inclination.scaffold.api.response.rolemenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RoleMenuManagerResponse {

	@ApiModelProperty(name="id",notes="id")
	private Integer id;

	@ApiModelProperty(name="roid",notes="权限id")
	private Integer roleId;

	@ApiModelProperty(name="tmid",notes="菜单id")
	private Integer menuId;
	
	@ApiModelProperty(name="rolename",notes="角色名称")
	private String roleName;
	
	@ApiModelProperty(name="menuname",notes="菜单名称")
	private String menuName;

}
