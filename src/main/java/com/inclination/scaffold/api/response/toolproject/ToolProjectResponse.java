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
	
	@ApiModelProperty(name = "urlname", value = "外部工具软件的名称")
	private String urlname;

	@ApiModelProperty(name = "url", value = "外部工具软件地址")
	private String url;
	
	@ApiModelProperty(name = "name", value = "工具软件的用户名")
	private String name;
	
	@ApiModelProperty(name = "url", value = "工具软件的密码")
	private String password;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
