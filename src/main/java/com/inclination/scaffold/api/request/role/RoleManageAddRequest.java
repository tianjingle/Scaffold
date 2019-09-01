package com.inclination.scaffold.api.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleManageAddRequest {

	@ApiModelProperty(name = "rolename", value = "角色名称")
	private String rolename;

	@ApiModelProperty(name = "menuid", value = "菜单名称")
	private String menuid;

	@ApiModelProperty(name = "content", value = "描述文字")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;


	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
