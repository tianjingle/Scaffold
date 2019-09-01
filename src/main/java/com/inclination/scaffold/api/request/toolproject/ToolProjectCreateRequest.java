package com.inclination.scaffold.api.request.toolproject;

import javax.validation.constraints.NotBlank;

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
public class ToolProjectCreateRequest {

	@ApiModelProperty(name = "urlname", value = "外部工具软件的名称")
	@NotBlank(message = "外部工具软件的名称不能为空")
	private String urlname;

	@ApiModelProperty(name = "url", value = "外部工具软件地址")
	@NotBlank(message = "外部工具软件的地址不能为空")
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
