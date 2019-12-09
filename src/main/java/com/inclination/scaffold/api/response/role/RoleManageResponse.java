package com.inclination.scaffold.api.response.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

@ApiModel
@Data
public class RoleManageResponse {
	
	@ApiModelProperty(name="id",value="角色的id")
	private Integer id;
	
	@ApiModelProperty(name = "rolename", value = "角色名称")
	private String roleName;

	@ApiModelProperty(name = "content", value = "描述文字")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;
}
