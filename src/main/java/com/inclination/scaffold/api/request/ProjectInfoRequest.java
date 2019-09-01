package com.inclination.scaffold.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProjectInfoRequest {

	@ApiModelProperty(name="projectName",value="工程名称")
	private String projectName;
	
	@ApiModelProperty(name="packageName",value="包名称")
	private String packageName;
	
	@ApiModelProperty(name="gitUrl",value="git地址")
	private String gitUrl;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	@Override
	public String toString() {
		return "ProjectInfoRequest [projectName=" + projectName + ", packageName=" + packageName + ", gitUrl=" + gitUrl
				+ "]";
	}
	
	
}
