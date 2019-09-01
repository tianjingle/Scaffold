package com.inclination.scaffold.api.request.user;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserManageLoginRequest {

	@NotBlank(message="用户名不能为空")
	@ApiModelProperty(name="username",value="用户名称")
	private String username;
	
	@NotBlank(message="用户密码不能为空")
	@ApiModelProperty(name="userpassword",value="用户密码")
	private String userpassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	
}
