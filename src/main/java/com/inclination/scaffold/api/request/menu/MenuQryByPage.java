package com.inclination.scaffold.api.request.menu;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuQryByPage extends BaseQueryRequestEntity{

	@ApiModelProperty(name = "mununame", value = "菜单名称")
	private String menuName;

	@ApiModelProperty(name = "content", value = "菜单描述")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;
	
}
