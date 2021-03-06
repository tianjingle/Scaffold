package com.inclination.scaffold.application.service;

import com.inclination.scaffold.api.entity.UserVo;
import com.inclination.scaffold.api.entity.UserFindByPageVo;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewDataOld;

/**
 * @Description: tianjingle
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
public interface UsersService {

	void UserAdd(UserVo entity) throws TException;

	void UserModify(UserVo entity) throws TException;

	void UserDelete(String id) throws TException;

	ViewDataOld findByPage(UserFindByPageVo entity);

	ViewDataOld findAll(UserVo entity);
}
