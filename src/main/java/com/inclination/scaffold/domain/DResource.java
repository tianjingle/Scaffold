package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.TErrorCode;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.infrastraction.repository.ResourceMapper;
import com.inclination.scaffold.infrastraction.repository.po.Resource;
import com.inclination.scaffold.utils.ModelMapUtils;

public class DResource {

	private Integer id;

	private String resourcename;

	private String resourceurl;

	private String content;

	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getResourceurl() {
		return resourceurl;
	}

	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
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

	/**
	 * 资源的添加
	 * 
	 * @param resourceMapper
	 *            数据库映射
	 * @throws TException
	 *             抛出错误
	 */
	public void resourceAdd(ResourceMapper resourceMapper) throws TException {
		// TODO Auto-generated method stub
		Resource po = ModelMapUtils.map(this, Resource.class);
		if (resourceMapper.countByName(po) != 0) {
			throw new TException(TErrorCode.ERROR_EXISIT_RESOURCE_CODE, TErrorCode.ERROR_EXISIT_RESOURCE_MSG);
		} else if (resourceMapper.insert(po) != 1) {
			throw new TException(TErrorCode.ERROR_INSERT_RESOURCE_CODE, TErrorCode.ERROR_INSERT_RESOURCE_MSG);
		}
	}

	/**
	 * 资源的修改
	 * 
	 * @param resourceMapper
	 *            数据库映射
	 * @throws TException
	 */
	public void resourceModify(ResourceMapper resourceMapper) throws TException {
		// TODO Auto-generated method stub
		Resource po = ModelMapUtils.map(this, Resource.class);
		if (resourceMapper.countByName(po) != 0) {
			throw new TException(TErrorCode.ERROR_EXISIT_RESOURCE_CODE, TErrorCode.ERROR_EXISIT_RESOURCE_MSG);
		} else if (resourceMapper.updateByPrimaryKey(po) != 1) {
			throw new TException(TErrorCode.ERROR_UPDATE_RESOURCE_CODE, TErrorCode.ERROR_UPDATE_RESOURCE_MSG);
		}
	}

	/**
	 * 资源的删除
	 * 
	 * @param resourceMapper
	 *            数据库映射
	 * @throws TException
	 */
	public void resourceDelete(ResourceMapper resourceMapper) throws TException {
		// TODO Auto-generated method stub
		if (resourceMapper.deleteByPrimaryKey(this.id) != 1) {
			throw new TException(TErrorCode.ERROR_DELETE_RESOURCE_CODE, TErrorCode.ERROR_DELETE_RESOURCE_MSG);
		}
	}

}
