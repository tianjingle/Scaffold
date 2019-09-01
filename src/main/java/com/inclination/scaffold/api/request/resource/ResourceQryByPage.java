package com.inclination.scaffold.api.request.resource;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResourceQryByPage extends BaseQueryRequestEntity {

	@ApiModelProperty(name = "resourcename", value = "资源名称")
	private String resourcename;

	@ApiModelProperty(name = "resourceurl", value = "资源路径")
	private String resourceurl;

	@ApiModelProperty(name = "content", value = "资源描述")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getResourceurl() {
		return resourceurl;
	}

	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
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
