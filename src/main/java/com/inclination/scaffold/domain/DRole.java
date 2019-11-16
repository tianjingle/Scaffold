package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.RoleMapper;
import com.inclination.scaffold.infrastraction.repository.po.Role;
import com.inclination.scaffold.utils.ModelMapUtils;

public class DRole {
	
	private Integer id;

	private String rolename;

	private String menuid;

	private String content;

	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
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

	public void addRole(RoleMapper roleMapping) throws TException {
		// TODO Auto-generated method stub
		Role po=ModelMapUtils.map(this, Role.class);
		if(roleMapping.countByRoleName(po)>1){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLE_CODE,TErrorCode.ERROR_EXISIT_ROLE_MSG);
		}else if(roleMapping.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_INSERT_ROLE_CODE,TErrorCode.ERROR_INSERT_ROLE_MSG);
		}
	}

	public void modifyRole(RoleMapper roleMapping) throws TException {
		// TODO Auto-generated method stub
		Role po=ModelMapUtils.map(this, Role.class);
		if(roleMapping.countByRoleName(po)>1){
			throw new TException(TErrorCode.ERROR_EXISIT_ROLE_CODE,TErrorCode.ERROR_EXISIT_ROLE_MSG);
		}else if(roleMapping.updateByPrimaryKey(po)<1){
			throw new TException(TErrorCode.ERROR_UPDATE_ROLE_CODE,TErrorCode.ERROR_UPDATE_ROLE_MSG);
		}
	}

	public void deleteRole(RoleMapper roleMapping) throws TException {
		// TODO Auto-generated method stub
		if(1>roleMapping.deleteByPrimaryKey(this.id)){
			throw new TException(TErrorCode.ERROR_DELETE_ROLE_CODE,TErrorCode.ERROR_DELETE_ROLE_MSG);
		}
	}

	
}
