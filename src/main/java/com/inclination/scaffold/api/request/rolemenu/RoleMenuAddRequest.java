package com.inclination.scaffold.api.request.rolemenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RoleMenuAddRequest {
	

	@ApiModelProperty(name="roleId",notes="权限id")
	private Integer roleId;

	@ApiModelProperty(name="menuId",notes="菜单id")
	private Integer menuId;

}
