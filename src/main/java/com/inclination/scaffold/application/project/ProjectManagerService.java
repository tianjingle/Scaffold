package com.inclination.scaffold.application.project;

import com.inclination.scaffold.api.request.project.ProjectQryByPage;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.ViewData;

import java.net.URISyntaxException;

public interface ProjectManagerService {


	void createScaffoldProject(ProjectInformationDto projectDto, UserDto dto) throws URISyntaxException;

	ViewData doSearchProject(ProjectQryByPage request, UserDto loginId);

    void createUserEnvironment(UserDto dto) throws Exception;

	boolean updateUserPassword(UserDto dto, UserPo userDto);

    void createUserOtherSystem(UserDto dto) throws Exception;
}
