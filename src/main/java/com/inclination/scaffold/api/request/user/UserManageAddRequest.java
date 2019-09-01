package com.inclination.scaffold.api.request.user;


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

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserManageAddRequest {
	
	@ApiModelProperty(name="userName",value="用户名称")
	@NotBlank(message="不能为空")
	private String username;
	
	@ApiModelProperty(name="loginid",value="手机号")
	@NotBlank(message="不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式错误")
	private String loginid;
	
	@ApiModelProperty(name="userpassword",value="用户密码")
	@NotBlank(message="不能为空")
	private String userpassword;
	
	@ApiModelProperty(name="useremil",value="邮箱")
	@NotBlank(message="不能为空")
	private String useremil;
	
	@ApiModelProperty(name="roid",value="权限")
	@NotNull(message="不能为空")
    private Integer roid;

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
