package com.inclination.scaffold.api.response.resource;

import java.util.List;

import com.inclination.scaffold.utils.BaseQueryResponseEntity;
import io.swagger.annotations.ApiModelProperty;
public class ResourceManagerQryResponse  extends BaseQueryResponseEntity{

	public void ResourceManagerQryResponse(int currentPage, int pageSize, int pageNum, int totalElement) {
		PageBaseQueryEntity(currentPage, pageSize, pageNum, totalElement);
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(name="list",value="查询结果")
	private List<ResourceManagerResponse> list;


	public List<ResourceManagerResponse> getList() {
		return list;
	}

	public void setList(List<ResourceManagerResponse> list) {
		this.list = list;
	}
}
