package com.inclination.scaffold.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.MenuResourceMapper;
import com.inclination.scaffold.infrastraction.repository.ResourceMapper;
import com.inclination.scaffold.infrastraction.repository.RoleMenuMapper;
import com.inclination.scaffold.infrastraction.repository.TmenuMapper;
import com.inclination.scaffold.infrastraction.repository.po.MenuResource;
import com.inclination.scaffold.infrastraction.repository.po.MenuResourceExample;
import com.inclination.scaffold.infrastraction.repository.po.Resource;
import com.inclination.scaffold.infrastraction.repository.po.ResourceExample;
import com.inclination.scaffold.infrastraction.repository.po.RoleMenu;
import com.inclination.scaffold.infrastraction.repository.po.RoleMenuExample;
import com.inclination.scaffold.infrastraction.repository.po.Tmenu;
import com.inclination.scaffold.infrastraction.repository.po.TmenuExample;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;

public class DMenu {
	private Integer id;

	private String mununame;

	private String content;

	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMununame() {
		return mununame;
	}

	public void setMununame(String mununame) {
		this.mununame = mununame;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void menuAdd(TmenuMapper menuMapping) throws TException {
		// TODO Auto-generated method stub
		Tmenu po=ModelMapUtils.map(this,Tmenu.class);
		if(menuMapping.countByName(po)!=0){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(menuMapping.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_INSERT_MENU_CODE,TErrorCode.ERROR_INSERT_MENU_MSG);
		}
	}

	public void menuModify(TmenuMapper menuMapping) throws TException {
		// TODO Auto-generated method stub
		Tmenu po=ModelMapUtils.map(this,Tmenu.class);
		if(menuMapping.countByName(po)!=0){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(menuMapping.updateByPrimaryKey(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_MENU_CODE,TErrorCode.ERROR_UPDATE_MENU_MSG);
		}
	}

	public void menuDelete(TmenuMapper menuMapping) throws TException {
		// TODO Auto-generated method stub
		if(menuMapping.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_MENU_CODE,TErrorCode.ERROR_DELETE_MENU_MSG);
		}
	}

	public ViewData findUserMenuAndResources(RoleMenuMapper roleMenuMapper, TmenuMapper menuMapping,
			MenuResourceMapper menuResourceMapper, ResourceMapper resourceMapper) {
		// TODO Auto-generated method stub

		RoleMenuExample example = new RoleMenuExample();
		RoleMenuExample.Criteria criteria=example.createCriteria();
		criteria.andRoidEqualTo(this.id);
		List<RoleMenu> rmlist=roleMenuMapper.selectByExample(example);
		Map map1=new HashMap();
		List list=new ArrayList();
		for(int i=0;i<rmlist.size();i++){
			list.add(rmlist.get(i).getTmid());
		}
		ViewData vd=new ViewData();
		List<Map> tianlist=new ArrayList<Map>();
		TmenuExample menuExample = new TmenuExample();
		TmenuExample.Criteria menuCriteria=menuExample.createCriteria();
		menuCriteria.andIdIn(list);
		List<Tmenu> menulist=menuMapping.selectByExample(menuExample);
		List mmrlist=new ArrayList();
		for(int i=0;i<menulist.size();i++){
			Map map = new HashMap();
			MenuResourceExample menuresourceExample=new MenuResourceExample();
			MenuResourceExample.Criteria menuresourceCriteria=menuresourceExample.createCriteria();
			menuresourceCriteria.andMenusidEqualTo(menulist.get(i).getId());
			List<MenuResource> mrlist=menuResourceMapper.selectByExample(menuresourceExample);
			for(int j=0;j<mrlist.size();j++){
				mmrlist.add(mrlist.get(j).getResourcesid());
			}
			ResourceExample resourceExample =new ResourceExample();
			ResourceExample.Criteria resourceCriteria=resourceExample.createCriteria();
			resourceCriteria.andIdIn(mmrlist);
			List<Resource> relist=resourceMapper.selectByExample(resourceExample);
			map.put("child", relist);
			map.put("name",menulist.get(i).getMununame());
			tianlist.add(map);
		}
		vd.setData(tianlist);
		vd.setTotal(rmlist.size());
		vd.setSuccess(true);
		return vd;
	}

}
