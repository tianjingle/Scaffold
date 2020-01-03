package com.inclination.scaffold.application.users;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.inclination.scaffold.api.request.user.UserQryByPages;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

public interface UserService {

	void createUser(UserDto userDto, UserDto dto) throws TException, Exception;

	ViewData userFind(@Valid UserQryByPages request, UserDto userdto);

	void deleteUser(UserDto dto) throws TException;

	void modifyUser(UserDto userDto, HttpSession session) throws TException;

	UserDto usersLogin(UserDto dto);;

	ViewData batchRemove(String userId);
}
