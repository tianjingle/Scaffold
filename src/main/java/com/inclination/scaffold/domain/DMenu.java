package com.inclination.scaffold.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.*;
import com.inclination.scaffold.infrastraction.repository.po.*;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import tk.mybatis.mapper.entity.Example;

public class DMenu {
	private Integer id;

	private String menuName;

	private String content;

	private String flag;

	public Integer getId() {
		return id;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getContent() {
		return content;
	}

	public String getFlag() {
		return flag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void menuAdd(MenuPoMapper menuMapping) throws TException {
		// TODO Auto-generated method stub
		MenuPo po=ModelMapUtils.map(this,MenuPo.class);
		Example example=new Example(MenuPo.class);
		example.createCriteria().andEqualTo("menuName",this.menuName);
		if(menuMapping.selectCountByExample(example)!=0){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(menuMapping.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_INSERT_MENU_CODE,TErrorCode.ERROR_INSERT_MENU_MSG);
		}
	}

	public void menuModify(MenuPoMapper menuMapping) throws TException {
		MenuPo po=ModelMapUtils.map(this,MenuPo.class);
		Example example=new Example(MenuPo.class);
		example.createCriteria().andEqualTo("menuName",this.menuName);
		if(menuMapping.selectCountByExample(example)!=0){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(menuMapping.updateByPrimaryKey(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_MENU_CODE,TErrorCode.ERROR_UPDATE_MENU_MSG);
		}
	}

	public void menuDelete(MenuPoMapper menuMapping) throws TException {
		// TODO Auto-generated method stub
		if(menuMapping.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_MENU_CODE,TErrorCode.ERROR_DELETE_MENU_MSG);
		}
	}

	public ViewData findUserMenuAndResources(RoleMenuPoMapper roleMenuMapper, MenuPoMapper menuMapping,
											 MenuResourcePoMapper menuResourceMapper, ResourcePoMapper resourceMapper) {
		// TODO Auto-generated method stub

		Example example = new Example(RoleMenuPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("roleId",this.id);
		List<RoleMenuPo> rmlist=roleMenuMapper.selectByExample(example);

		//角色-菜单
		List<Map> tianlist=new ArrayList<Map>();
		Example menuExample = new Example(MenuPo.class);
		Example.Criteria menuCriteria=menuExample.createCriteria();
		menuCriteria.andIn("id",rmlist.stream().map(RoleMenuPo::getMenuId).collect(Collectors.toList()));
		List<MenuPo> menulist=menuMapping.selectByExample(menuExample);
		//获取改角色的所有菜单
		for(int i=0;i<menulist.size();i++){
			Map map = new HashMap();
			Example example1=new Example(MenuResourcePo.class);
			example1.createCriteria().andEqualTo("menuId",menulist.get(i).getId());
			//加载该菜单下的所有菜单-资源关系
			List<MenuResourcePo> mrlist=menuResourceMapper.selectByExample(example1);
			List<ResourcePo> relist=new ArrayList<>();
			if(mrlist.size()>0){
				Example resourceExample =new Example(ResourcePo.class);
				Example.Criteria resourceCriteria=resourceExample.createCriteria();
				resourceCriteria.andIn("id",mrlist.stream().map(MenuResourcePo::getResourceId).collect(Collectors.toList()));
				relist=resourceMapper.selectByExample(resourceExample);
			}
			map.put("child", relist);
			map.put("name",menulist.get(i).getMenuName());
			tianlist.add(map);
		}
		return ViewData.success(tianlist);
	}

}
