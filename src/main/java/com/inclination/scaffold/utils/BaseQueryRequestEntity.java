package com.inclination.scaffold.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@ApiModel
public class BaseQueryRequestEntity {
	

	
	@ApiParam(name="limit",value="每页条数")	
	private int limit = 15;// 每页的记录数
	@ApiParam(name="start",value="当前页数")
	private int start = 0;// 第几条开始
	@ApiParam(name="start",value="当前页数")
	private int page = 1; //第几页
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	
}
