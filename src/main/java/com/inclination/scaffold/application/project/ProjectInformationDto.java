package com.inclination.scaffold.application.project;

import java.util.Date;

public class ProjectInformationDto {

	private Integer id;

	private String loginid;

	private String giturl;

	private String jenkinsurl;

	private String apollourl;

	private String artifactid;

	private String groupid;

	private String version;

	private String gitorg;

	private String apolloorg;

	private Date createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
