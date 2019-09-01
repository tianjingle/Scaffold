package com.inclination.scaffold.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseQueryResponseEntity {

	@ApiModelProperty(name="total",value="总记录数")
	private int total;
	
	@ApiModelProperty(name="总条数",value="总记录数")	
	private int pageSize;
	
	@ApiModelProperty(name="curPage",value="当前页数")	
	private int curPage;
	
	@ApiModelProperty(name="pagetotal",value="总页数")
	private int pagetotal;
	
	
	/**
	 * 分页
	 * @param currentPage 当前页数
	 * @param pageSize 每页条数
	 * @param pageNum 总页数
	 * @param totalElement 总条数
	 */
	public void PageBaseQueryEntity(int currentPage,int pageSize,int pageNum,int totalElement){
		this.curPage=currentPage;
		this.pageSize=pageSize;
		this.pagetotal=pageNum;
		this.total=totalElement;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getCurPage() {
		return curPage;
	}


	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getPagetotal() {
		return pagetotal;
	}


	public void setPagetotal(int pagetotal) {
		this.pagetotal = pagetotal;
	}

	
}
