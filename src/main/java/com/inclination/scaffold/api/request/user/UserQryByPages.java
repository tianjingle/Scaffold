package com.inclination.scaffold.api.request.user;

import java.io.Serializable;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserQryByPages extends BaseQueryRequestEntity implements Serializable{
	

	@ApiModelProperty(name="userName",value="用户名称")
	private String userName;
	
	@ApiModelProperty(name="loginid",value="手机号")
	private String loginid;
	
	@ApiModelProperty(name="userPassword",value="用户密码")
	private String userpassword;
	
	@ApiModelProperty(name="userEmil",value="邮箱")
	private String useremil;
	
	@ApiModelProperty(name="roid",value="权限")
    private Integer roid;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
