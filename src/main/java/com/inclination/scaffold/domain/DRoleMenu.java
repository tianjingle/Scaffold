package com.inclination.scaffold.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerAllResponse;
import com.inclination.scaffold.api.response.rolemenu.RoleMenuManagerResponse;
import com.inclination.scaffold.constant.TErrorCode;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.infrastraction.repository.RoleMenuMapper;
import com.inclination.scaffold.infrastraction.repository.TmenuMapper;
import com.inclination.scaffold.infrastraction.repository.po.RoleMenu;
import com.inclination.scaffold.infrastraction.repository.po.RoleMenuExample;
import com.inclination.scaffold.infrastraction.repository.po.Tmenu;
import com.inclination.scaffold.utils.ModelMapUtils;

public class DRoleMenu {
	
	private Integer id;

	private Integer roid;

	private Integer tmid;

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

	public Integer getRoid() {
		return roid;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}

	public Integer getTmid() {
		return tmid;
	}

	public void setTmid(Integer tmid) {
		this.tmid = tmid;
	}
	/**
	 * 角色资源的插入
	 * @param roleMenuMapper
	 * @throws TException
	 */
	public void addRoleMenu(RoleMenuMapper roleMenuMapper) throws TException {
		// TODO Auto-generated method stub
		RoleMenu po=ModelMapUtils.map(this, RoleMenu.class);
		if(roleMenuMapper.countbyCount(po)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLEMENU_CODE,TErrorCode.ERROR_EXISIT_ROLEMENU_MSG);
		}else if(roleMenuMapper.insert(po)!=1){
		    throw new TException(TErrorCode.ERROR_INSERT_ROLEMENU_CODE,TErrorCode.ERROR_INSERT_ROLEMENU_MSG);
		}
	}
	/**
	 * 角色菜单修改
	 * @param roleMenuMapper
	 * @throws TException
	 */
	public void modifyRoleMenu(RoleMenuMapper roleMenuMapper) throws TException {
		// TODO Auto-generated method stub
		RoleMenu po=ModelMapUtils.map(this, RoleMenu.class);
		RoleMenuExample example =new RoleMenuExample();
		RoleMenuExample.Criteria criteria=example.createCriteria();
		criteria.andRoidEqualTo(this.roid);
		criteria.andTmidEqualTo(this.tmid);
		roleMenuMapper.deleteByExample(example);
		if("Y".equals(this.flag)){
			roleMenuMapper.insert(po);
		}
/*		if(roleMenuMapper.countbyCount(po)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLEMENU_CODE,TErrorCode.ERROR_EXISIT_ROLEMENU_MSG);
		}else if(roleMenuMapper.updateByPrimaryKey(po)!=1){
		    throw new TException(TErrorCode.ERROR_UPDATE_ROLEMENU_CODE,TErrorCode.ERROR_UPDATE_ROLEMENU_MSG);
		}*/
	}

	public void deleteRoleMenu(RoleMenuMapper roleMenuMapper) throws TException {
		// TODO Auto-generated method stub
		if(roleMenuMapper.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_ROLEMENU_CODE,TErrorCode.ERROR_DELETE_ROLEMENU_MSG);
		}
	}

	public RoleMenuManagerAllResponse findMyMenuWithFlag(TmenuMapper menuMapping, RoleMenuMapper roleMenuMapper) {
		// TODO Auto-generated method stub
		List<Tmenu> menulist=menuMapping.selectBySelective(new Tmenu());
		Map<String,Object> map=new HashMap<>();
		RoleMenuExample example=new RoleMenuExample();
		RoleMenuExample.Criteria criteria=example.createCriteria();
		criteria.andRoidEqualTo(this.roid);
		List<RoleMenu> roleMenuList=roleMenuMapper.selectByExample(example);
		for(int i=0;i<roleMenuList.size();i++){
			map.put(String.valueOf(roleMenuList.get(i).getTmid()), roleMenuList.get(i));
		}
		for(int i=0;i<menulist.size();i++){
			if(map.get(menulist.get(i).getId())!=null){
				menulist.get(i).setFlag("Y");
			}else{
				menulist.get(i).setFlag("N");
			}
		}
		RoleMenuManagerAllResponse response =new RoleMenuManagerAllResponse();
		response.setList(ModelMapUtils.map(menulist, RoleMenuManagerResponse.class));
		return response;
	}

}
