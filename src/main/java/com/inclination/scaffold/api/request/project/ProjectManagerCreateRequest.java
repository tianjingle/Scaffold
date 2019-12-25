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

	
	@ApiModelProperty(name="artifactid",value="工程名称")
	@NotBlank
	private String artifactId;

	@ApiModelProperty(name="groupid",value="项目包名")
	@NotBlank
	private String groupId;

	@ApiModelProperty(name="version",value="项目版本号")
	@NotBlank
	private String version;

	@ApiModelProperty(name="gitorg",value="git组织名称")
	private String gitOrg;

	@ApiModelProperty(name="apolloorg",value="apollo的组织")
	private String apolloOrg;

}
