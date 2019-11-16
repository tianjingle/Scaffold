package com.inclination.scaffold.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ProjectInfoRequest {

	@ApiModelProperty(name="projectName",value="工程名称")
	private String projectName;
	
	@ApiModelProperty(name="packageName",value="包名称")
	private String packageName;
	
	@ApiModelProperty(name="gitUrl",value="git地址")
	private String gitUrl;


	@Override
	public String toString() {
		return "ProjectInfoRequest [projectName=" + projectName + ", packageName=" + packageName + ", gitUrl=" + gitUrl
				+ "]";
	}
	
	
}
