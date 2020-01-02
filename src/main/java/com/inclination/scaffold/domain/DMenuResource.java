package com.inclination.scaffold.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inclination.scaffold.api.response.resource.ResourceManagerResponse;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.MenuResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.ResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.*;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import tk.mybatis.mapper.entity.Example;

public class DMenuResource {

	private Integer id;

	private Integer menuId;

	private Integer resourceId;
	
	private String flag;

	public Integer getId() {
		return id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public String getFlag() {
		return flag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 菜单与资源之间关系的添加
	 * @param menuResourceMapper
	 * @throws TException 
	 */
	public void addMenuResource(MenuResourcePoMapper menuResourceMapper) throws TException {
		// TODO Auto-generated method stub
		MenuResourcePo po=ModelMapUtils.map(this,MenuResourcePo.class);
		if("Y".equals(this.flag)){
			if(menuResourceMapper.insert(po)!=1){
				throw new TException(TErrorCode.ERROR_INSERT_MENURESOURCE_CODE,TErrorCode.ERROR_INSERT_MENURESOURCE_MSG);
			}
		}
/*
		if(menuResourceMapper.countBySame(po)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_MENURESOURCE_CODE,TErrorCode.ERROR_EXISIT_MENURESOURCE_MSG);
		}else{
			if(menuResourceMapper.insert(po)!=1){
				throw new TException(TErrorCode.ERROR_INSERT_MENURESOURCE_CODE,TErrorCode.ERROR_INSERT_MENURESOURCE_MSG);
			}
		}*/
	}

	/**
	 * 资源菜单关系的修改
	 * @param menuResourceMapper 数据库服务
	 * @throws TException 
	 */
	public void modifyMenuResource(MenuResourcePoMapper menuResourceMapper) throws TException {
		// TODO Auto-generated method stub
		MenuResourcePo po=ModelMapUtils.map(this,MenuResourcePo.class);
		Example example=new Example(MenuResourcePo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("menuId",this.getMenuId());
		criteria.andEqualTo("resourceid",this.resourceId);
		if(menuResourceMapper.selectCountByExample(example)>=1){
			throw new TException(TErrorCode.ERROR_EXISIT_MENURESOURCE_CODE,TErrorCode.ERROR_EXISIT_MENURESOURCE_MSG);
		}else{
			if(menuResourceMapper.updateByPrimaryKey(po)!=1){
				throw new TException(TErrorCode.ERROR_UPDATE_MENURESOURCE_CODE,TErrorCode.ERROR_UPDATE_MENURESOURCE_MSG);
			}
		}
	}
	/**
	 * 资源菜单关系的删除
	 * @param menuResourceMapper 数据库服务
	 * @throws TException
	 */
	public void deleteMenuResource(MenuResourcePoMapper menuResourceMapper) throws TException {
		// TODO Auto-generated method stub
		if(menuResourceMapper.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_MENURESOURCE_CODE,TErrorCode.ERROR_DELETE_MENURESOURCE_MSG);
		}
	}

	public ViewData findMyResourceWithFlag(ResourcePoMapper resourceMapper, MenuResourcePoMapper menuResourceMapper) {
		// TODO Auto-generated method stub
		List<ResourcePo> resourceList=resourceMapper.selectAll();
		Example example=new Example(MenuResourcePo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("menuId",this.menuId);
		List<MenuResourcePo> menuResourceList=menuResourceMapper.selectByExample(example);
		Map<String,Object> map=new HashMap<>();
		for(int i=0;i<menuResourceList.size();i++){
			map.put(String.valueOf(menuResourceList.get(i).getResourceId()), menuResourceList.get(i));
		}
		for (int i=0;i<resourceList.size();i++) {
			if(map.get(String.valueOf(resourceList.get(i).getId()))!=null){
				resourceList.get(i).setFlag("Y");
			}else{
				resourceList.get(i).setFlag("N");
			}
		}
		return ViewData.success(ModelMapUtils.map(resourceList, ResourceManagerResponse.class));
	}

}
