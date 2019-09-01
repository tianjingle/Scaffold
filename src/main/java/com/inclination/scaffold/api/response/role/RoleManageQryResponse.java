package com.inclination.scaffold.api.response.role;

import java.util.List;

import com.inclination.scaffold.utils.BaseQueryResponseEntity;

import io.swagger.annotations.ApiModelProperty;

public class RoleManageQryResponse extends BaseQueryResponseEntity{
	
	public void RoleManagerQryResponse(int currentPage, int pageSize, int pageNum, int totalElement) {
		PageBaseQueryEntity(currentPage, pageSize, pageNum, totalElement);
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(name="list",value="查询结果")
	private List<RoleManageResponse> list;


	public List<RoleManageResponse> getList() {
		return list;
	}

	public void setList(List<RoleManageResponse> list) {
		this.list = list;
	}
	
}
