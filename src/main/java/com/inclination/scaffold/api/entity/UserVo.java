package com.inclination.scaffold.api.entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(name="loginId",value = "用户登录号，手机号")
	private java.lang.String loginId;
	
    /**
     * 用户名称
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="userName",value = "用户名称")
	private java.lang.String userName;
	
    /**
     * 用户密码
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="userPassword",value = "用户密码")
	private java.lang.String userPassword;
	
    /**
     * 用户电子邮箱
     */
	@NotBlank(message="不能为空")
    @ApiModelProperty(name="userEmil",value = "用户电子邮箱")
	private java.lang.String userEmil;
	
    /**
     * 用户权限标志
     */
	@NotNull(message="不能为空")
    @ApiModelProperty(name="roId",value = "用户权限标志")
	private Integer roId;
	
}