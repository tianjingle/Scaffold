package com.inclination.scaffold.api.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserManageLoginResponse {

	@ApiModelProperty(name="id",value="用户seq")
	private int id;
	
	@ApiModelProperty(name="username",value="用户名称")
	private String userName;
	
	@ApiModelProperty(name="loginid",value="手机号")
	private String loginId;
	
	@ApiModelProperty(name="useremil",value="邮箱")
	private String userEmil;

	@ApiModelProperty(name="roid",value="權限")
	private int roId;
}
