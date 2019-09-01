package com.inclination.scaffold.api.response.user;

import java.util.List;

import com.inclination.scaffold.utils.BaseQueryResponseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserManagerQryResponse extends BaseQueryResponseEntity{
	
	public void UserManagerQryResponse(int currentPage, int pageSize, int pageNum, int totalElement) {
		PageBaseQueryEntity(currentPage, pageSize, pageNum, totalElement);
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(name="list",value="查询结果")
	private List<UserManageResponse> list;


	public List<UserManageResponse> getList() {
		return list;
	}

	public void setList(List<UserManageResponse> list) {
		this.list = list;
	}
	
	
	
}
