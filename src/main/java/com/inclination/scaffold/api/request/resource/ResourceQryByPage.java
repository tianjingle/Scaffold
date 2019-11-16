package com.inclination.scaffold.api.request.resource;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ResourceQryByPage extends BaseQueryRequestEntity {

	@ApiModelProperty(name = "resourcename", value = "资源名称")
	private String resourceName;

	@ApiModelProperty(name = "resourceurl", value = "资源路径")
	private String resourceUrl;

	@ApiModelProperty(name = "content", value = "资源描述")
	private String content;

	@ApiModelProperty(name = "flag", value = "标志")
	private String flag;

}
