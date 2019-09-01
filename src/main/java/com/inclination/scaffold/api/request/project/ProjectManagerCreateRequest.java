package com.inclination.scaffold.api.request.project;


import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectManagerCreateRequest {

	@ApiModelProperty(name="giturl",value="git地址")
	@NotBlank
	private String giturl;

	@ApiModelProperty(name="jenkinsurl",value="jenkins地址")
	@NotBlank
	private String jenkinsurl;

	@ApiModelProperty(name="apollourl",value="apollo地址")
	@NotBlank
	private String apollourl;
	
	@ApiModelProperty(name="artifactid",value="工程名称")
	@NotBlank
	private String artifactid;

	@ApiModelProperty(name="groupid",value="项目包名")
	@NotBlank
	private String groupid;

	@ApiModelProperty(name="version",value="项目版本号")
	@NotBlank
	private String version;

	@ApiModelProperty(name="gitorg",value="git组织名称")
	@NotBlank
	private String gitorg;

	@ApiModelProperty(name="apolloorg",value="apollo的组织")
	@NotBlank
	private String apolloorg;

	public String getGiturl() {
		return giturl;
	}

	public void setGiturl(String giturl) {
		this.giturl = giturl;
	}

	public String getJenkinsurl() {
		return jenkinsurl;
	}

	public void setJenkinsurl(String jenkinsurl) {
		this.jenkinsurl = jenkinsurl;
	}

	public String getApollourl() {
		return apollourl;
	}

	public void setApollourl(String apollourl) {
		this.apollourl = apollourl;
	}

	public String getArtifactid() {
		return artifactid;
	}

	public void setArtifactid(String artifactid) {
		this.artifactid = artifactid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGitorg() {
		return gitorg;
	}

	public void setGitorg(String gitorg) {
		this.gitorg = gitorg;
	}

	public String getApolloorg() {
		return apolloorg;
	}

	public void setApolloorg(String apolloorg) {
		this.apolloorg = apolloorg;
	}

	
}
