package com.inclination.scaffold.api.request.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ProjectManagerGitCreateRequest {

	@ApiModelProperty(name="projectName",value="项目名称")
	private String projectName;

	@ApiModelProperty(name="gitUrl",value="git地址")
	private String gitUrl;
	
}
