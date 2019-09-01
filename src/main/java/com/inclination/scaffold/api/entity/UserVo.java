package com.inclination.scaffold.api.entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: tianjingle
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="user对象", description="tianjingle")
public class UserVo {
    
    /**
     * id
     */
    @ApiModelProperty(name="id",value = "id")
	private java.lang.Integer id;
	
    /**
     * 用户登录号，手机号
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="loginid",value = "用户登录号，手机号")
	private java.lang.String loginid;
	
    /**
     * 用户名称
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="username",value = "用户名称")
	private java.lang.String username;
	
    /**
     * 用户密码
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="userpassword",value = "用户密码")
	private java.lang.String userpassword;
	
    /**
     * 用户电子邮箱
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="useremil",value = "用户电子邮箱")
	private java.lang.String useremil;
	
    /**
     * 用户权限标志
     */
	@NotNull(message="不能为空")
    @ApiModelProperty(name="roid",value = "用户权限标志")
	private Integer roid;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

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