package com.inclination.scaffold.api.request.menu;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MenuQryByPage extends BaseQueryRequestEntity{

	@ApiModelProperty(name = "mununame", value = "菜单名称")
	private String mununame;

	@ApiModelProperty(name = "content", value = "菜单描述")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;

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
