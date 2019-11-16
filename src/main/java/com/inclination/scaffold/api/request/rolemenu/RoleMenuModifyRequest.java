package com.inclination.scaffold.api.request.rolemenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RoleMenuModifyRequest {
	@ApiModelProperty(name="id",notes="id")
	private Integer id;

	@ApiModelProperty(name="roleId",notes="权限id")
	private Integer roleId;

	@ApiModelProperty(name="menuId",notes="菜单id")
	private Integer menuId;

	@ApiModelProperty(name="flag",notes="有无标志")
	private String flag;
}
