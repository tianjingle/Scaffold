package com.inclination.scaffold.api.response.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResourceManagerResponse {

	@ApiModelProperty(name = "id", notes = "seq")
	private Integer id;
	
	@ApiModelProperty(name = "resourcename", notes = "资源名称")
	private String resourcename;
	
	@ApiModelProperty(name = "resourceurl", notes = "资源路径")
	private String resourceurl;
	
	@ApiModelProperty(name = "content", notes = "描述")
	private String content;
	
	@ApiModelProperty(name = "flag", notes = "标致")
	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
