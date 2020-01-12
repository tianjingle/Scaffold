package com.inclination.scaffold.api.interfaces;

import javax.validation.Valid;

import com.google.common.base.Strings;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.resource.ResourceAddRequest;
import com.inclination.scaffold.api.request.resource.ResourceModifyRequest;
import com.inclination.scaffold.api.request.resource.ResourceQryByPage;
import com.inclination.scaffold.application.resource.ResourceDto;
import com.inclination.scaffold.application.resource.ResourceService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@RestController
@ApiModel
public class RourceManagerApi {

	/**
	 * 注入操作资源的服务
	 */
	@Autowired
	private ResourceService resourceService;
	/**
	 * 资源管理的添加
	 * @param request 添加的资源信息
	 * @throws TException
	 */
	@PostMapping(value="/resources-manager",consumes="application/json",produces="application/json;charset=utf-8")
	@ApiOperation(value="资源添加",notes="资源添加")
	public void resourceAdd(@Valid @RequestBody ResourceAddRequest request) throws TException{
		resourceService.addResource(ModelMapUtils.map(request, ResourceDto.class));
	}
	/**
	 * 资源管理的修改
	 * @param request 资源修改的内容
	 * @throws TException 抛出错误
	 */
	@PatchMapping(value="/resource-manager",consumes="application/json",produces="application/json;charset=utf-8")
	@ApiOperation(value="资源修改",notes="资源修改")
	public void resourceModify(@Valid @RequestBody ResourceModifyRequest request) throws TException{
		resourceService.modifyResource(ModelMapUtils.map(request,ResourceDto.class));
	}
	/**
	 * 资源管理的删除
	 * @param id 删除资源的id
	 * @throws TException 抛出错误
	 */
	@DeleteMapping(value="/resource-manager/{id}")
	@ApiOperation(value="资源删除",notes="资源删除")
	public void resourceDelete(@PathVariable String id) throws TException{
		ResourceDto dto=new ResourceDto();
		dto.setId(Integer.valueOf(id));
		resourceService.deleteResource(dto);
	}
	/**
	 * 根据资源条件进行模糊查询
	 * @param request 模糊条件
	 * @return 返回list
	 */
	@GetMapping(value="/resource-manager")
	@ApiOperation(value="资源查询",notes="资源查询")
	public ViewData resourceFinds(@ModelAttribute ResourceQryByPage request){
		return resourceService.resourceQryByPages(request);
	}

	/**
	 * 批量删除资源
	 * @param resourceIds
	 * @return
	 */
	@DeleteMapping(value = "/resource-batch-delete")
	@ApiOperation(value = "资源批量删除",notes = "资源批量删除")
	public ViewData batchRemoveResource(String resourceIds) throws TException {
		if (Strings.isNullOrEmpty(resourceIds)){
			throw new TException(TErrorCode.ERROR_DELETE_RESOURCE_CODE,TErrorCode.ERROR_DELETE_RESOURCE_MSG);
		}
		return resourceService.batchRemove(resourceIds);
	}


	@GetMapping(value = "/resource-all")
	@ApiOperation(value="获取所有资料",notes = "获取所有资料")
	public ViewData getResourceAll(){
		return resourceService.getResourceAll();
	}
}
