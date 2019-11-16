package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.RolePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.RolePo;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

public class DRole {
	
	private Integer id;

	private String roleName;

	private String menuId;

	private String content;

	private String flag;

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getMenuId() {
		return menuId;
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

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void addRole(RolePoMapper roleMapping) throws TException {
		// TODO Auto-generated method stub
		RolePo po=ModelMapUtils.map(this, RolePo.class);
		Example example=new Example(RolePo.class);
		Example.Criteria criteria=example.createCriteria().andEqualTo("roleName",po.getRoleName());
		if(roleMapping.selectCountByExample(example)>1){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLE_CODE,TErrorCode.ERROR_EXISIT_ROLE_MSG);
		}else if(roleMapping.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_INSERT_ROLE_CODE,TErrorCode.ERROR_INSERT_ROLE_MSG);
		}
	}

	public void modifyRole(RolePoMapper roleMapping) throws TException {
		// TODO Auto-generated method stub
		RolePo po=ModelMapUtils.map(this, RolePo.class);
		Example example=new Example(RolePo.class);
		Example.Criteria criteria=example.createCriteria().andEqualTo("roleName",po.getRoleName());
		if(roleMapping.selectCountByExample(example)>1){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLE_CODE,TErrorCode.ERROR_EXISIT_ROLE_MSG);
		}else if(roleMapping.updateByPrimaryKey(po)<1){
			throw new TException(TErrorCode.ERROR_UPDATE_ROLE_CODE,TErrorCode.ERROR_UPDATE_ROLE_MSG);
		}
	}

	public void deleteRole(RolePoMapper roleMapping) throws TException {
		// TODO Auto-generated method stub
		if(1>roleMapping.deleteByPrimaryKey(this.id)){
			throw new TException(TErrorCode.ERROR_DELETE_ROLE_CODE,TErrorCode.ERROR_DELETE_ROLE_MSG);
		}
	}
	
}
