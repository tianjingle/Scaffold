package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.TErrorCode;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.infrastraction.repository.ToolprojecturlMapper;
import com.inclination.scaffold.infrastraction.repository.po.Toolprojecturl;
import com.inclination.scaffold.infrastraction.repository.po.ToolprojecturlExample;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.ApiModelProperty;

public class DToolProject {
	
	private Integer id;
	
	private String urlname;

	private String url;

	private String name;
	
	private String password;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void toolProjectAdd(ToolprojecturlMapper toolprojecturlMapper) throws TException {
		// TODO Auto-generated method stub
		Toolprojecturl po=ModelMapUtils.map(this, Toolprojecturl.class);
		ToolprojecturlExample example=new ToolprojecturlExample();
		ToolprojecturlExample.Criteria criteria=example.createCriteria();
		criteria.andUrlnameEqualTo(this.urlname);
		if(toolprojecturlMapper.countByExample(example)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_CODE,TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_MSG);
		}else{
			if(toolprojecturlMapper.insert(po)!=1){
				throw new TException(TErrorCode.ERROR_INSERT_TOOLPROJECTURL_CODE,TErrorCode.ERROR_INSERT_TOOLPROJECTURL_MSG);
			}
		}
		
	}

	public void toolProjectModify(ToolprojecturlMapper toolprojecturlMapper) throws TException {
		// TODO Auto-generated method stub
		Toolprojecturl po=ModelMapUtils.map(this, Toolprojecturl.class);
		ToolprojecturlExample example=new ToolprojecturlExample();
		ToolprojecturlExample.Criteria criteria=example.createCriteria();
		criteria.andUrlnameEqualTo(this.urlname);
		if(toolprojecturlMapper.countByExample(example)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_CODE,TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_MSG);
		}else{
			if(toolprojecturlMapper.updateByPrimaryKey(po)!=1){
				throw new TException(TErrorCode.ERROR_UPDATE_TOOLPROJECTURL_CODE,TErrorCode.ERROR_UPDATE_TOOLPROJECTURL_MSG);
			}
		}
	}

	public void toolProjectDelete(ToolprojecturlMapper toolprojecturlMapper) throws TException {
		// TODO Auto-generated method stub
		Toolprojecturl po=ModelMapUtils.map(this, Toolprojecturl.class);
		if(toolprojecturlMapper.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_TOOLPROJECTURL_CODE,TErrorCode.ERROR_DELETE_TOOLPROJECTURL_MSG);
		}
	}
	
	
}
