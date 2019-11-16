package com.inclination.scaffold.api.response.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ResourceManagerResponse {

	@ApiModelProperty(name = "id", notes = "seq")
	private Integer id;
	
	@ApiModelProperty(name = "resourcename", notes = "资源名称")
	private String resourceName;
	
	@ApiModelProperty(name = "resourceurl", notes = "资源路径")
	private String resourceUrl;
	
	@ApiModelProperty(name = "content", notes = "描述")
	private String content;
	
	@ApiModelProperty(name = "flag", notes = "标致")
	private String flag;
	
}
