package com.inclination.scaffold.api.interfaces;

import java.net.URISyntaxException;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.inclination.scaffold.api.request.project.ProjectQryByPage;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inclination.scaffold.api.request.project.ProjectManagerCreateRequest;
import com.inclination.scaffold.api.request.project.ProjectManagerGitCreateRequest;
import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.project.ProjectManagerGitCreateDto;
import com.inclination.scaffold.application.project.ProjectManagerService;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @dis:   this class manager scaffold project create
 * @author tianjingle 
 * @email: 2695062879@qq.com   
 * @tel:   15652466911
 * @time:  2019.06.15
 * all right protect
 */
@RestController
@Api
public class ProjectManagerApi {
	
	/**
	 * 注入工程管理服务
	 */
	@Autowired
	private ProjectManagerService projectManagerService;

	
	/**
	 * 创建脚手架工程
	 * @param request 脚手架工程的具体信息
	 * @param session sessioin
	 */
	@PostMapping(value="/projects-scaffold-create")
	@ApiOperation(value="创建脚手架工程",notes="创建工程")
	public void scaffoldProjectCreate(@Valid @RequestBody ProjectManagerCreateRequest request,HttpSession session) throws URISyntaxException {
		UserDto dto=(UserDto) session.getAttribute("CurrentUser");
		ProjectInformationDto projectDto=ModelMapUtils.map(request, ProjectInformationDto.class);
		projectDto.setCreateTime(new Date());
		projectDto.setLoginId(dto.getLoginId());
		projectManagerService.createScaffoldProject(projectDto,dto);
	}

	/**
	 * 查询项目资料
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/projects-scaffold-find")
	@ApiOperation(value="查询项目资料",notes = "查询项目资料")
	public ViewData parseScaffold(@ModelAttribute ProjectQryByPage request,@SessionAttribute("CurrentUser") UserDto user){
		return projectManagerService.doSearchProject(request,user.getLoginId());
	}
}
