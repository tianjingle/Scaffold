package com.inclination.scaffold.api.request.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProjectManagerGitCreateRequest {

	@ApiModelProperty(name="projectName",value="项目名称")
	private String projectName;

	@ApiModelProperty(name="gitUrl",value="git地址")
	private String gitUrl;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}
	
	
}
