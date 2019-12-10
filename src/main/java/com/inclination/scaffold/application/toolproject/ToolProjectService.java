package com.inclination.scaffold.application.toolproject;

import com.inclination.scaffold.api.response.toolproject.ToolProjectQryAllResponse;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

public interface ToolProjectService {

	void addToolProject(ToolProjectDto map) throws TException;

	void modifyToolProject(ToolProjectDto map) throws TException;

	void deleteToolProject(ToolProjectDto dto) throws TException;

	ViewData findAll();


}
