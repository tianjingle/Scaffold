package com.inclination.scaffold.api.request.user;

import java.io.Serializable;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserQryByPages extends BaseQueryRequestEntity implements Serializable{

	@ApiModelProperty(name="userName",value="用户名称")
	private String userName;
	
	@ApiModelProperty(name="loginid",value="手机号")
	private String loginId;
	
	@ApiModelProperty(name="userPassword",value="用户密码")
	private String userPassword;
	
	@ApiModelProperty(name="userEmil",value="邮箱")
	private String userEmil;
	
	@ApiModelProperty(name="roid",value="权限")
    private Integer roId;
}
