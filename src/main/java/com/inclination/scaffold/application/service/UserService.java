package com.inclination.scaffold.application.service;

import com.inclination.scaffold.api.entity.UserVo;
import com.inclination.scaffold.api.entity.UserFindByPageVo;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.utils.ViewData;

/**
 * @Description: tianjingle
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
public interface UserService {

	void UserAdd(UserVo entity) throws TException;

	void UserModify(UserVo entity) throws TException;

	void UserDelete(String id) throws TException;

	ViewData findByPage(UserFindByPageVo entity);

	ViewData findAll(UserVo entity);
}
