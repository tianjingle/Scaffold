package com.inclination.scaffold.application.project;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectInformationDto {

	private Integer id;

	private String loginId;

	private String gitUrl;

	private String jenkinsUrl;

	private String apolloUrl;

	private String artifactId;

	private String groupId;

	private String version;

	private String gitOrg;

	private String apolloOrg;

	private Date createTime;

}
