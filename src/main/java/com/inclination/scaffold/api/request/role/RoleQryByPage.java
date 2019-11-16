package com.inclination.scaffold.api.request.role;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RoleQryByPage extends BaseQueryRequestEntity{

	@ApiModelProperty(name = "rolename", value = "角色名称")
	private String roleName;

	@ApiModelProperty(name = "menuid", value = "菜单名称")
	private String menuId;

	@ApiModelProperty(name = "content", value = "描述文字")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;
}
