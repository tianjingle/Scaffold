package com.inclination.scaffold.application.project;

import com.inclination.scaffold.api.request.project.ProjectQryByPage;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.utils.ViewData;

public interface ProjectManagerService {

	void createGitRepository(ProjectManagerGitCreateDto map, UserDto dto);

	void createScaffoldProject(ProjectInformationDto projectDto, UserDto dto);

	ViewData doSearchProject(ProjectQryByPage request, String loginId);
}
