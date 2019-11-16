package com.inclination.scaffold.application.toolproject;

import com.inclination.scaffold.api.response.toolproject.ToolProjectQryAllResponse;
import com.inclination.scaffold.constant.exception.TException;

public interface ToolProjectService {

	void addToolProject(ToolProjectDto map) throws TException;

	void modifyToolProject(ToolProjectDto map) throws TException;

	void deleteToolProject(ToolProjectDto dto) throws TException;

	ToolProjectQryAllResponse findAll();


}
