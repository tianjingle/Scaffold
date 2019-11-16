package com.inclination.scaffold.api.request.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ResourceModifyRequest {


	@ApiModelProperty(name = "id", notes = "id")
	private Integer id;
	
	@ApiModelProperty(name = "resourcename", notes = "资源名称")
	private String resourceName;
	
	@ApiModelProperty(name = "resourceurl", notes = "资源路径")
	private String resourceUrl;
	
	@ApiModelProperty(name = "content", notes = "描述内容")
	private String content;
	
	@ApiModelProperty(name = "flag", notes = "标志")
	private String flag;


}
