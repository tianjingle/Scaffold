package com.inclination.scaffold.api.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.user.UserManageAddRequest;
import com.inclination.scaffold.api.request.user.UserManageLoginRequest;
import com.inclination.scaffold.api.request.user.UserManageModifyRequest;
import com.inclination.scaffold.api.request.user.UserQryByPages;
import com.inclination.scaffold.api.response.user.UserManageLoginResponse;
import com.inclination.scaffold.api.response.user.UserManagerQryResponse;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.application.users.UserService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @version 1.0
 * @author tianjingle All right protected time:2019:6:2
 *
 */
@RestController
@Api
public class UserManageApi {

	/**
	 * 注入操作用户的类
	 */
	@Autowired
	private UserService userManageServiceImp;
	/**
	 * 添加用户
	 * @param user 用户
	 * @throws Exception 
	 */
	@PostMapping(value="/user-add",consumes="application/json",produces = "application/json;charset=utf-8")
	@ApiOperation(value="创建用户",notes="创建用户")
	public void userAdd(@Valid @RequestBody UserManageAddRequest user) throws Exception{
		UserDto dto=ModelMapUtils.map(user, UserDto.class);
		userManageServiceImp.createUser(dto);
		
	}
	/**
	 * 查询所有用户
	 * @return 返回查询的结果
	 */
	@GetMapping(value="/user-find",produces = "application/json;charset=utf-8")
	@ApiOperation(value="查询用户",notes="查询用户")
	public UserManagerQryResponse userQry(@ModelAttribute UserQryByPages request){
		return userManageServiceImp.userFind(request);
	}
	/**
	 * 删除用户
	 * @throws TException 
	 */
	@DeleteMapping(value="/user-delete/{id}",produces="application/json;charset=utf-8")
	@ApiOperation(value="删除用户",notes="删除用户")
	public void userDelete(@PathVariable String id) throws TException{
		UserDto dto=new UserDto();
		dto.setId(Integer.parseInt(id));
		userManageServiceImp.deleteUser(dto);
	}
	/**
	 * 修改用户
	 * @throws TException 
	 */
	@PatchMapping(value="/user-update",produces="application/json;charset=utf-8")
	@ApiOperation(value="修改用户",notes="修改用户")
	public void userModify(@RequestBody UserManageModifyRequest request) throws TException{
		UserDto dto=ModelMapUtils.map(request, UserDto.class);
		userManageServiceImp.modifyUser(dto);
	}
	
	@GetMapping(value="/user-time-out",produces="application/json;charset=utf-8")
	@ApiOperation(value="是否超时",notes="判断用户是否超时")
	public ViewData timeOut(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		ViewData vd=new ViewData();
        if(null!=session.getAttribute("CurrentUser")&&!"".equals(session.getAttribute("1"))){
        	vd.setSuccess(true);
        }else{
        	vd.setSuccess(true);
        }
        return vd;
	}
	@PostMapping(value="/users-login")
	public UserManageLoginResponse userLogin(@Valid @RequestBody UserManageLoginRequest request,HttpSession session){
		UserDto dto=ModelMapUtils.map(request, UserDto.class);
		UserDto loginDto=userManageServiceImp.usersLogin(dto);
		UserManageLoginResponse response=ModelMapUtils.map(loginDto, UserManageLoginResponse.class);
		if(response!=null){
			session.setAttribute("CurrentUser", loginDto);
		}else{
			return null;
		}
		return response;
	}
}
