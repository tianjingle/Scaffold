package com.inclination.scaffold.infrastraction.repository;

import com.inclination.scaffold.infrastraction.repository.po.ResourcePo;
import com.inclination.scaffold.utils.MyMapper;

import java.util.List;

public interface ResourcePoMapper extends MyMapper<ResourcePo> {
    /**
     * 模糊搜
     * @param po
     * @return
     */
    List<ResourcePo> selectBySelective(ResourcePo po);
}