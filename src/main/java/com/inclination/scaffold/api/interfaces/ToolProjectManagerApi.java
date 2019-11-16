package com.inclination.scaffold.api.interfaces;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.toolproject.ToolProjectCreateRequest;
import com.inclination.scaffold.api.request.toolproject.ToolProjectModifyRequest;
import com.inclination.scaffold.api.response.toolproject.ToolProjectQryAllResponse;
import com.inclination.scaffold.application.toolproject.ToolProjectDto;
import com.inclination.scaffold.application.toolproject.ToolProjectService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class ToolProjectManagerApi {

	/**
	 * 注入外部插件的服务
	 */
	@Autowired
	private ToolProjectService toolProjectService;
	
	/**
	 * 新增外部工具软件的地址
	 * @param request
	 * @throws TException 
	 */
	@PostMapping(value="/tools-projects-manager")
	@ApiOperation(value="新增外部工具项目信息",notes="新增外部工具项目信息")
	public void toolProjectCreate(@Valid @RequestBody ToolProjectCreateRequest request) throws TException{
		toolProjectService.addToolProject(ModelMapUtils.map(request, ToolProjectDto.class));
	}
	
	@PatchMapping(value="/tools-projects-manager")
	@ApiOperation(value="修改外部工具项目信息",notes="修改外部工具项目的信息")
	public void toolProjectModify(@Valid @RequestBody ToolProjectModifyRequest request) throws TException{
		toolProjectService.modifyToolProject(ModelMapUtils.map(request, ToolProjectDto.class));
	}
	@DeleteMapping(value="/tools-projects-manager/{id}")
	@ApiOperation(value="删除外部工具项目信息",notes="删除外部工具项目的信息")
	public void toolProjectDelete(@PathVariable String id) throws TException{
		ToolProjectDto dto=new ToolProjectDto();
		dto.setId(Integer.parseInt(id));
		toolProjectService.deleteToolProject(dto);
	}
	@GetMapping(value="/tools-projects-manager")
	@ApiOperation(value="获取外部工具项目信息",notes="获取外部工具项目的信息")
	public ToolProjectQryAllResponse findToolProjectAll(){
		return toolProjectService.findAll();
	}
	
}
