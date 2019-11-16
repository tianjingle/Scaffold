package com.inclination.scaffold.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ProjectInfoResponse {
	
	@ApiModelProperty(name="工程名称",value="工程名称")
	private String projectName;
	
	@ApiModelProperty(name="包名称",value="包名称")
	private String packageName;
	
	@ApiModelProperty(name="git地址",value="git地址")
	private String gitUrl;

	
	
}
