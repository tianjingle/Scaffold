package com.inclination.scaffold.application.project;

import com.inclination.scaffold.application.users.UserDto;

public interface ProjectManagerService {

	void createGitRepository(ProjectManagerGitCreateDto map, UserDto dto);

	void createScaffoldProject(ProjectInformationDto projectDto, UserDto dto);

}
