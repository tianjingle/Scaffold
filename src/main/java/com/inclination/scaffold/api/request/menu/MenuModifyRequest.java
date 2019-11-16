package com.inclination.scaffold.api.request.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuModifyRequest {
	
	@ApiModelProperty(name="id",value="菜单id")
	private Integer id;
	
	@ApiModelProperty(name = "mununame", notes = "菜单名称")
	private String menuName;

	@ApiModelProperty(name = "content", notes = "菜单描述")
	private String content;

	@ApiModelProperty(name = "flag", notes = "标志")
	private String flag;

}
