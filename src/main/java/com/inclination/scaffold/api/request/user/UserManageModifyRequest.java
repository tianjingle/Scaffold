package com.inclination.scaffold.api.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserManageModifyRequest {
	
	@ApiModelProperty(name="用户seq",value="用户seq")
	private int id;
	
	@ApiModelProperty(name="用户名称",value="用户名称")
	private String username;
	
	@ApiModelProperty(name="手机号",value="手机号")
	private String loginid;
	
	@ApiModelProperty(name="用户密码",value="用户密码")
	private String userpassword;
	
	@ApiModelProperty(name="用户邮箱",value="邮箱")
	private String useremil;

	@ApiModelProperty(name="权限",value="权限")
    private Integer roid;

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

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUseremil() {
		return useremil;
	}

	public void setUseremil(String useremil) {
		this.useremil = useremil;
	}

	public Integer getRoid() {
		return roid;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}


	

	
}
