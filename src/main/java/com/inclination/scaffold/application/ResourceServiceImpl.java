package com.inclination.scaffold.application;

import java.util.Arrays;
import java.util.List;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.infrastraction.repository.ResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ResourcePo;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.api.request.resource.ResourceQryByPage;
import com.inclination.scaffold.api.response.resource.ResourceManagerQryResponse;
import com.inclination.scaffold.api.response.resource.ResourceManagerResponse;
import com.inclination.scaffold.application.resource.ResourceDto;
import com.inclination.scaffold.application.resource.ResourceService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DResource;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class ResourceServiceImpl implements ResourceService{

	/**
	 * 注入资源的数据库服务
	 */
	@Autowired
	private ResourcePoMapper resourceMapper;
	
	@Override
	@Transactional
	public void addResource(ResourceDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DResource.class).resourceAdd(resourceMapper);
	}

	@Override
	@Transactional
	public void modifyResource(ResourceDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DResource.class).resourceModify(resourceMapper);
	}

	@Override
	@Transactional
	public void deleteResource(ResourceDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DResource.class).resourceDelete(resourceMapper);
	}

	@Override
	public ViewData resourceQryByPages(ResourceQryByPage request) {
		// TODO Auto-generated method stub
		ResourcePo po=ModelMapUtils.map(request, ResourcePo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		Example example=new Example(ResourcePo.class);
		example.createCriteria().andLike("resourceName",request.getResourceName()).orLike("resourceUrl",request.getResourceName());
		List<ResourcePo> list=resourceMapper.selectByExample(example);
		return ViewData.success(ModelMapUtils.map(list, ResourceManagerResponse.class),hpage.getPages(),hpage.getTotal());
	}

	@Override
	@Transactional
	public ViewData batchRemove(String resourceIds) throws TException {
		Example example=new Example(ResourcePo.class);
		example.createCriteria().andIn("id", Arrays.asList(resourceIds.split(",")));
		if (resourceMapper.deleteByExample(example)<1){
			throw new TException(TErrorCode.ERROR_DELETE_RESOURCE_CODE, TErrorCode.ERROR_DELETE_RESOURCE_MSG);
		}
		return ViewData.success(true);
	}

}
