package com.inclination.scaffold.api.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserManageLoginResponse {

	@ApiModelProperty(name="id",value="用户seq")
	private int id;
	
	@ApiModelProperty(name="username",value="用户名称")
	private String username;
	
	@ApiModelProperty(name="loginid",value="手机号")
	private String loginid;
	
	@ApiModelProperty(name="useremil",value="邮箱")
	private String useremil;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getUseremil() {
		return useremil;
	}

	public void setUseremil(String useremil) {
		this.useremil = useremil;
	}
	
	
}
