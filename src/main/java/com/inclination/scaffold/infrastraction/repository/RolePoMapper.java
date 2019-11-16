package com.inclination.scaffold.infrastraction.repository;

import com.inclination.scaffold.infrastraction.repository.po.RolePo;
import com.inclination.scaffold.utils.MyMapper;

import java.util.List;

public interface RolePoMapper extends MyMapper<RolePo> {

    /***
     * 模糊搜
     * @param po
     * @return
     */
    List<RolePo> selectBySelective(RolePo po);
}