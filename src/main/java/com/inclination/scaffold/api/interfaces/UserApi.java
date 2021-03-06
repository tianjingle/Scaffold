package com.inclination.scaffold.api.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import com.inclination.scaffold.api.entity.UserVo;
import com.inclination.scaffold.api.entity.UserFindByPageVo;
import com.inclination.scaffold.application.service.UsersService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewDataOld;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: sdfjldf
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */


@Api(tags="sdfjldf")
@RestController
@RequestMapping("/user/user")
public class UserApi {



	/**
	 * 注入菜单服务
	 */
	@Autowired
	private UsersService userService;
	
	/**
	  * 添加
	 * @param userVo
	 * @return
	 */
    @ApiOperation(value="sdfjldf-添加", notes="sdfjldf-添加")
	@PostMapping(value = "/add")
	public void add(@Valid @RequestBody UserVo userVo) throws TException {
		userService.UserAdd(userVo);
	}

	/**
	 * 修改
	 * @param userVo user
	 * @throws TException 
	 */
	@ApiOperation(value="sdfjldf-编辑", notes="sdfjldf-编辑")
	@PatchMapping(value = "/edit")
	public void modify(@ModelAttribute UserVo userVo) throws TException {
		userService.UserModify(userVo);
	}


	/**
	  *通过id删除
	 * @param id
	 * @return
	 */
	@ApiOperation(value="sdfjldf-通过id删除", notes="sdfjldf-通过id删除")
	@DeleteMapping(value = "/delete/{id}")
	public void menuDelete(@PathVariable String id) throws TException{
		userService.UserDelete(id);
	}


	/**
	 * 分页查询
	 * @param entity 查询条件
	 * @return 返回查询的结果
	 */
	@GetMapping(value="/query-page")
	@ApiOperation(value="sdfjldf-分页查询", notes="sdfjldf-分页查询")
	public ViewDataOld findByPage(@ModelAttribute UserFindByPageVo entity){
		return userService.findByPage(entity);
	}


	/**
	 * 查询用户的访问菜单列表
	 * @param  entity
	 * @return viewdate
	 */
	@GetMapping(value="/finds-all")
	@ApiOperation(value="sdfjldf-查询所有", notes="sdfjldf-按条件查询所有")
	public ViewDataOld find(@ModelAttribute UserVo entity){
		return userService.findAll(entity);
	}
}
