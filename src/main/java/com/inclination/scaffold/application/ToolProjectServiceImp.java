package com.inclination.scaffold.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inclination.scaffold.api.response.toolproject.ToolProjectQryAllResponse;
import com.inclination.scaffold.api.response.toolproject.ToolProjectResponse;
import com.inclination.scaffold.application.toolproject.ToolProjectDto;
import com.inclination.scaffold.application.toolproject.ToolProjectService;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.domain.DToolProject;
import com.inclination.scaffold.infrastraction.repository.ToolprojecturlMapper;
import com.inclination.scaffold.infrastraction.repository.po.Toolprojecturl;
import com.inclination.scaffold.infrastraction.repository.po.ToolprojecturlExample;
import com.inclination.scaffold.utils.ModelMapUtils;

@Service
public class ToolProjectServiceImp implements ToolProjectService {

	/**
	 * 创建工具软件管理的数据库服务
	 */
	@Autowired
	private ToolprojecturlMapper toolprojecturlMapper;
	/**
	 * 创建工具软件的添加
	 * @throws TException 
	 */
	@Override
	@Transactional
	public void addToolProject(ToolProjectDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DToolProject.class).toolProjectAdd(toolprojecturlMapper);
	}
	@Override
	@Transactional
	public void modifyToolProject(ToolProjectDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DToolProject.class).toolProjectModify(toolprojecturlMapper);
	}
	@Override
	@Transactional
	public void deleteToolProject(ToolProjectDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DToolProject.class).toolProjectDelete(toolprojecturlMapper);
	}
	@Override
	public ToolProjectQryAllResponse findAll() {
		// TODO Auto-generated method stub
		ToolprojecturlExample example = new ToolprojecturlExample();
		ToolprojecturlExample.Criteria criteria=example.createCriteria();
		criteria.andIdIsNotNull();
		List<Toolprojecturl> list=toolprojecturlMapper.selectByExample(example);
		ToolProjectQryAllResponse response=new ToolProjectQryAllResponse();
		response.setList(ModelMapUtils.map(list, ToolProjectResponse.class));
		return response;
	}

}
