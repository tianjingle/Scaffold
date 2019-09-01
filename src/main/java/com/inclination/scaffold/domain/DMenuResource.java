package com.inclination.scaffold.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inclination.scaffold.api.response.resource.ResourceManagerAllResponse;
import com.inclination.scaffold.api.response.resource.ResourceManagerResponse;
import com.inclination.scaffold.constant.TErrorCode;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.infrastraction.repository.MenuResourceMapper;
import com.inclination.scaffold.infrastraction.repository.ResourceMapper;
import com.inclination.scaffold.infrastraction.repository.po.MenuResource;
import com.inclination.scaffold.infrastraction.repository.po.MenuResourceExample;
import com.inclination.scaffold.infrastraction.repository.po.Resource;
import com.inclination.scaffold.utils.ModelMapUtils;

public class DMenuResource {

	private Integer id;

	private Integer menusid;

	private Integer resourcesid;
	
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenusid() {
		return menusid;
	}

	public void setMenusid(Integer menusid) {
		this.menusid = menusid;
	}

	public Integer getResourcesid() {
		return resourcesid;
	}

	public void setResourcesid(Integer resourcesid) {
		this.resourcesid = resourcesid;
	}
	/**
	 * 菜单与资源之间关系的添加
	 * @param menuResourceMapper数据库服务
	 * @throws TException 
	 */
	public void addMenuResource(MenuResourceMapper menuResourceMapper) throws TException {
		// TODO Auto-generated method stub
		MenuResource po=ModelMapUtils.map(this,MenuResource.class);
	    MenuResourceExample example=new MenuResourceExample();
	    MenuResourceExample.Criteria criteria=example.createCriteria();
	    criteria.andMenusidEqualTo(this.menusid);
	    criteria.andResourcesidEqualTo(this.resourcesid);
		menuResourceMapper.deleteByExample(example);
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
	public void modifyMenuResource(MenuResourceMapper menuResourceMapper) throws TException {
		// TODO Auto-generated method stub
		MenuResource po=ModelMapUtils.map(this,MenuResource.class);
		if(menuResourceMapper.countBySame(po)>=1){
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
	public void deleteMenuResource(MenuResourceMapper menuResourceMapper) throws TException {
		// TODO Auto-generated method stub
		if(menuResourceMapper.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_MENURESOURCE_CODE,TErrorCode.ERROR_DELETE_MENURESOURCE_MSG);
		}
	}

	public ResourceManagerAllResponse findMyResourceWithFlag(ResourceMapper resourceMapper,
			MenuResourceMapper menuResourceMapper) {
		// TODO Auto-generated method stub
		
		List<Resource> resourceList=resourceMapper.selectBySelective(new Resource());
		MenuResourceExample example=new MenuResourceExample();
		MenuResourceExample.Criteria criteria=example.createCriteria();
		criteria.andMenusidEqualTo(this.menusid);
		List<MenuResource> menuResourceList=menuResourceMapper.selectByExample(example);
		Map<String,Object> map=new HashMap<>();
		for(int i=0;i<menuResourceList.size();i++){
			map.put(String.valueOf(menuResourceList.get(i).getResourcesid()), menuResourceList.get(i));
		}
		for (int i=0;i<resourceList.size();i++) {
			if(map.get(String.valueOf(resourceList.get(i).getId()))!=null){
				resourceList.get(i).setFlag("Y");
			}else{
				resourceList.get(i).setFlag("N");
			}
		}
		ResourceManagerAllResponse response=new ResourceManagerAllResponse();
		response.setList(ModelMapUtils.map(resourceList, ResourceManagerResponse.class));
		return response;
	}

}
