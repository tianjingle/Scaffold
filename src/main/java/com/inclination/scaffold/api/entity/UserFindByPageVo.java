package com.inclination.scaffold.api.entity;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserFindByPageVo extends BaseQueryRequestEntity{
    
    /**
     *用户登录号，手机号
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="loginId",value = "用户登录号，手机号")
	private java.lang.String loginId;
    /**
     *用户名称
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="userName",value = "用户名称")
	private java.lang.String userName;
    /**
     *用户密码
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="userPassword",value = "用户密码")
	private java.lang.String userPassword;
    /**
     *用户电子邮箱
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="userEmil",value = "用户电子邮箱")
	private java.lang.String userEmil;
    /**
     *用户权限标志
     */
    @NotBlank(message="不能为空")
    @ApiModelProperty(name="roid",value = "用户权限标志")
	private Integer roid;

}
