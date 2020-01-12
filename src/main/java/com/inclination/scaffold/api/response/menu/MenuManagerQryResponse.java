package com.inclination.scaffold.api.response.menu;

import java.util.List;

import com.inclination.scaffold.api.response.role.RoleManageResponse;
import com.inclination.scaffold.utils.BaseQueryResponseEntity;

import io.swagger.annotations.ApiModelProperty;

public class MenuManagerQryResponse  extends BaseQueryResponseEntity{

	public void MenuManagerQryResponse(int currentPage, int pageSize, int pageNum, int totalElement) {
		PageBaseQueryEntity(currentPage, pageSize, pageNum, totalElement);
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(name="list",value="查询结果")
	private List<MenuManagerResponse> list;


	public List<MenuManagerResponse> getList() {
		return list;
	}

	public void setList(List<MenuManagerResponse> list) {
		this.list = list;
	}
}
