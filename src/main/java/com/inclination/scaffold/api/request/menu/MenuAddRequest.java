package com.inclination.scaffold.api.request.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuAddRequest {

	@ApiModelProperty(value = "menuName", notes = "菜单名称")
	private String menuName;

	@ApiModelProperty(value = "content", notes = "菜单描述")
	private String content;

	@ApiModelProperty(value = "flag", notes = "标志")
	private String flag;
}
