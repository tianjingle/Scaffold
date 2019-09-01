/*package com.inclination.scaffold.api.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.ProjectInfoRequest;
import com.inclination.scaffold.api.response.ProjectInfoResponse;
import com.inclination.scaffold.application.project.ProjectManagerGitCreateDto;
import com.inclination.scaffold.application.project.ProjectCreateService;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
*//**
 * @version 1.0
 * @author tianjingle
 *
 *//*
@RestController
@ApiModel
public class ScaffoldCreateApi {
	
	@Autowired
	private ProjectCreateService projectCreateServiceImp;
	*//**
	 * 创建脚手架工程
	 * @param reqeust 项目中必要参数
	 * @return 脚手架工程创建的结果
	 *//*
	@PostMapping(value="/project-create",consumes="application/json",produces="application/json")
	@ApiOperation(value="脚手架创建",notes="创建脚手架")
	public ProjectInfoResponse projectCreate(@RequestBody ProjectInfoRequest reqeust){
		projectCreateServiceImp.createProject(ModelMapUtils.map(reqeust, ProjectManagerGitCreateDto.class));
		return null;
	}

	@GetMapping(value="/projects-find",consumes="application/json",produces="application/json")
	@ApiOperation(value="查询所有的脚手架工程",notes="查询脚手架工程")
	public String projectsFindAll(){
		return "ok";
	}
}
*/