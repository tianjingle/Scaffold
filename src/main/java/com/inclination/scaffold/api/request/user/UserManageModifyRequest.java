package com.inclination.scaffold.api.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserManageModifyRequest {
	
	@ApiModelProperty(name="用户seq",value="用户seq")
	private int id;
	
	@ApiModelProperty(name="用户名称",value="用户名称")
	private String userName;
	
	@ApiModelProperty(name="手机号",value="手机号")
	private String loginId;
	
	@ApiModelProperty(name="用户密码",value="用户密码")
	private String userPassword;
	
	@ApiModelProperty(name="用户邮箱",value="邮箱")
	private String userEmil;

	@ApiModelProperty(name="权限",value="权限")
    private Integer roId;
	
}
