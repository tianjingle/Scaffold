package com.inclination.scaffold.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inclination.scaffold.api.response.menuresource.MenuResourceQryResponse;
import com.inclination.scaffold.api.response.resource.ResourceManagerAllResponse;
import com.inclination.scaffold.application.menuresource.MenuResourceDto;
import com.inclination.scaffold.application.menuresource.MenuResourceService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DMenuResource;
import com.inclination.scaffold.infrastraction.repository.MenuResourceMapper;
import com.inclination.scaffold.infrastraction.repository.ResourceMapper;
import com.inclination.scaffold.utils.ModelMapUtils;

@Service
public class MenuResourceServiceImp implements MenuResourceService {

	/**
	 * 注入数据库服务
	 */
	@Autowired
	private MenuResourceMapper menuResourceMapper;

	/**
	 * 注入资源的resource
	 */
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	@Transactional
	public void menuResourceAdd(MenuResourceDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DMenuResource.class).addMenuResource(menuResourceMapper);
	}

	@Override
	@Transactional
	public void menuResourceModify(MenuResourceDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DMenuResource.class).modifyMenuResource(menuResourceMapper);
	}

	@Override
	@Transactional
	public void menuResourceDelete(MenuResourceDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DMenuResource.class).deleteMenuResource(menuResourceMapper);
	}

	@Override
	@Transactional
	public MenuResourceQryResponse menuResourceFinds() {
		// TODO Auto-generated method stub
		MenuResourceQryResponse response=new MenuResourceQryResponse();
		response.setList(menuResourceMapper.findAll());
		return response;
	}

	@Override
	public ResourceManagerAllResponse menuResourceFindsNew(Integer menuid) {
		// TODO Auto-generated method stub
		
		DMenuResource domain=new DMenuResource();
		domain.setMenusid(menuid);
		return domain.findMyResourceWithFlag(resourceMapper,menuResourceMapper);

	}
	
	
}
