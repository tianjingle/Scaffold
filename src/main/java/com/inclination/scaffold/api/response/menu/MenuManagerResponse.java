package com.inclination.scaffold.api.response.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MenuManagerResponse {

	@ApiModelProperty(value = "id", notes = "菜单id")
	private Integer id;

	@ApiModelProperty(value = "mununame", notes = "菜单名称")
	private String mununame;

	@ApiModelProperty(value = "content", notes = "菜单描述")
	private String content;

	@ApiModelProperty(value = "flag", notes = "标志")
	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMununame() {
		return mununame;
	}

	public void setMununame(String mununame) {
		this.mununame = mununame;
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
