package com.inclination.scaffold.infrastraction.repository;

import com.inclination.scaffold.infrastraction.repository.po.MenuPo;
import com.inclination.scaffold.utils.MyMapper;

import java.util.List;

public interface MenuPoMapper extends MyMapper<MenuPo> {
    /**
     * 模糊检索
     * @param po
     * @return
     */
    List<MenuPo> selectBySelective(MenuPo po);
}