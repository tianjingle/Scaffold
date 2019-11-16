package com.inclination.scaffold.api.response.menuresource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuResourceResponse {

	@ApiModelProperty(name = "id", notes = "id")
	private Integer id;
	
	@ApiModelProperty(name = "menuid", notes = "菜单id")
	private Integer menuId;

	@ApiModelProperty(name = "resourceid", notes = "资源id")
	private Integer resourceId;

	@ApiModelProperty(name = "menuname", notes = "菜单名称")
	private String menuName;

	@ApiModelProperty(name = "resourcename", notes = "资源名称")
	private String resourceName;

}
