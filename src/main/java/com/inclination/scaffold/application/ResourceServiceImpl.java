package com.inclination.scaffold.application;

import java.util.List;

import com.inclination.scaffold.infrastraction.repository.ResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ResourcePo;
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
	public ResourceManagerQryResponse resourceQryByPages(ResourceQryByPage request) {
		// TODO Auto-generated method stub
		ResourcePo po=ModelMapUtils.map(request, ResourcePo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<ResourcePo> list=resourceMapper.selectBySelective(po);
		ResourceManagerQryResponse response = new ResourceManagerQryResponse();
		response.setList(ModelMapUtils.map(list, ResourceManagerResponse.class));
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return response;
	}

}
