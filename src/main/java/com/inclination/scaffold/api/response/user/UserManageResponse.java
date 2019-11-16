package com.inclination.scaffold.api.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserManageResponse {
	
	@ApiModelProperty(name="id",value="用户seq")
	private int id;
	
	@ApiModelProperty(name="username",value="用户名称")
	private String userName;
	
	@ApiModelProperty(name="loginid",value="手机号")
	private String loginId;
	
	@ApiModelProperty(name="userpassword",value="用户密码")
	private String userPassword;
	
	@ApiModelProperty(name="useremil",value="邮箱")
	private String userEmil;

	@ApiModelProperty(name="roid",value="权限")
    private Integer roId;

	


}
