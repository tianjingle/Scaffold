package com.inclination.scaffold.api.response.rolemenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RoleMenuManagerResponse {

	@ApiModelProperty(name="id",notes="id")
	private Integer id;

	
	@ApiModelProperty(name="menuname",notes="菜单名称")
	private String menuName;


	@ApiModelProperty(name="flag",notes="标志")
	private String flag;
}
