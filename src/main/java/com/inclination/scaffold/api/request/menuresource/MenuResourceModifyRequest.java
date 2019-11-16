package com.inclination.scaffold.api.request.menuresource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuResourceModifyRequest {

	@ApiModelProperty(name = "id", notes = "id")
	private Integer id;
	
	@ApiModelProperty(name = "menuid", notes = "菜单id")
	private Integer menuId;

	@ApiModelProperty(name = "resourceid", notes = "资源id")
	private Integer resourceId;

	@ApiModelProperty(name="flag",notes="有无标志")
	private String flag;

}
