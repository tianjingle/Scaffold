package com.inclination.scaffold.application;

import com.inclination.scaffold.api.request.menuresource.MenuResourceAddRequest;
import com.inclination.scaffold.api.response.menuresource.MenuResourceResponse;
import com.inclination.scaffold.infrastraction.repository.MenuResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.ResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.MenuResourcePo;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inclination.scaffold.api.response.menuresource.MenuResourceQryResponse;
import com.inclination.scaffold.application.menuresource.MenuResourceDto;
import com.inclination.scaffold.application.menuresource.MenuResourceService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DMenuResource;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class MenuResourceServiceImpl implements MenuResourceService {

	/**
	 * 注入数据库服务
	 */
	@Autowired
	private MenuResourcePoMapper menuResourceMapper;

	/**
	 * 注入资源的resource
	 */
	@Autowired
	private ResourcePoMapper resourceMapper;
	
	@Override
	@Transactional
	public void menuResourceAdd(String id, MenuResourceAddRequest[] dto) throws TException {
		// TODO Auto-generated method stub
		Example example=new Example(MenuResourcePo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("menuId",id);
		menuResourceMapper.deleteByExample(example);
		for(int i=0;i<dto.length;i++){
			MenuResourceDto temp=ModelMapUtils.map(dto[i], MenuResourceDto.class);
			ModelMapUtils.map(temp, DMenuResource.class).addMenuResource(menuResourceMapper);
		}

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
		response.setList(ModelMapUtils.map(menuResourceMapper.selectAll(), MenuResourceResponse.class));
		return response;
	}

	@Override
	public ViewData menuResourceFindsNew(Integer menuid) {
		// TODO Auto-generated method stub
		
		DMenuResource domain=new DMenuResource();
		domain.setMenuId(menuid);
		return domain.findMyResourceWithFlag(resourceMapper,menuResourceMapper);

	}
	
	
}
