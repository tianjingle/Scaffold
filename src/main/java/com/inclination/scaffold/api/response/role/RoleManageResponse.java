package com.inclination.scaffold.api.response.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleManageResponse {
	
	@ApiModelProperty(name="id",value="角色的id")
	private Integer id;
	
	@ApiModelProperty(name = "rolename", value = "角色名称")
	private String roleName;

	@ApiModelProperty(name = "menuid", value = "菜单名称")
	private String menuId;

	@ApiModelProperty(name = "content", value = "描述文字")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;
	
	
}
