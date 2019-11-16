package com.inclination.scaffold.api.response.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuManagerResponse {

	@ApiModelProperty(value = "id", notes = "菜单id")
	private Integer id;

	@ApiModelProperty(value = "mununame", notes = "菜单名称")
	private String menuName;

	@ApiModelProperty(value = "content", notes = "菜单描述")
	private String content;

	@ApiModelProperty(value = "flag", notes = "标志")
	private String flag;

}
