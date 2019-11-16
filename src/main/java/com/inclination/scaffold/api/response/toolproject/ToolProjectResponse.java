package com.inclination.scaffold.api.response.toolproject;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ToolProjectResponse {

	@ApiModelProperty(name = "id", value = "外部工具软件主键id")
	private String id;
	
	@ApiModelProperty(name = "name", value = "外部工具软件的名称")
	private String name;

	@ApiModelProperty(name = "url", value = "外部工具软件地址")
	private String url;
	
	@ApiModelProperty(name = "name", value = "工具软件的用户名")
	private String userName;
	
	@ApiModelProperty(name = "url", value = "工具软件的密码")
	private String userPassword;
	
}
