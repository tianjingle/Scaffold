package com.inclination.scaffold.infrastraction.repository;

import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.MyMapper;

import java.util.List;

public interface UserPoMapper extends MyMapper<UserPo> {
    /**
     * 查询账户和密码
     * @param po
     * @return
     */
    Integer findUserByNamePass(UserPo po);

    /**
     * 模糊查询
     * @param po
     * @return
     */
    List<UserPo> selectBySelective(UserPo po);

    List<UserPo> selectBySelectiveByAdmin(UserPo po);
}