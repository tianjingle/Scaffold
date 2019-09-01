package com.inclination.scaffold.api.entity;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserFindByPageVo extends BaseQueryRequestEntity{
    
    /**
     *用户登录号，手机号
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="loginid",value = "用户登录号，手机号")
	private java.lang.String loginid;
    /**
     *用户名称
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="username",value = "用户名称")
	private java.lang.String username;
    /**
     *用户密码
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="userpassword",value = "用户密码")
	private java.lang.String userpassword;
    /**
     *用户电子邮箱
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="useremil",value = "用户电子邮箱")
	private java.lang.String useremil;
    /**
     *用户权限标志
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="roid",value = "用户权限标志")
	private java.lang.Integer roid;
	public java.lang.String getLoginid() {
		return loginid;
	}
	public void setLoginid(java.lang.String loginid) {
		this.loginid = loginid;
	}
	public java.lang.String getUsername() {
		return username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	public java.lang.String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(java.lang.String userpassword) {
		this.userpassword = userpassword;
	}
	public java.lang.String getUseremil() {
		return useremil;
	}
	public void setUseremil(java.lang.String useremil) {
		this.useremil = useremil;
	}
	public java.lang.Integer getRoid() {
		return roid;
	}
	public void setRoid(java.lang.Integer roid) {
		this.roid = roid;
	}

}
