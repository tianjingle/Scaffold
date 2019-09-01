package com.inclination.scaffold.application.users;

import javax.validation.Valid;

import com.inclination.scaffold.api.request.user.UserQryByPages;
import com.inclination.scaffold.api.response.user.UserManageLoginResponse;
import com.inclination.scaffold.api.response.user.UserManagerQryResponse;
import com.inclination.scaffold.constant.config.TException;

public interface UserService {

	void createUser(UserDto dto) throws TException, Exception;

	UserManagerQryResponse userFind(@Valid UserQryByPages request);

	void deleteUser(UserDto dto) throws TException;

	void modifyUser(UserDto dto) throws TException;

	UserDto usersLogin(UserDto dto);; 
}
