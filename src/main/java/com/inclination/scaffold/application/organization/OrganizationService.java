package com.inclination.scaffold.application.organization;

import com.inclination.scaffold.api.request.organization.OrganizationFindRequest;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ViewData;

public interface OrganizationService {
    /**
     * 添加机构
     * @param map
     * @return
     */
    ViewData add(OrganizationDto map) throws TException;

    /**
     * 编辑
     * @param map
     * @return
     */
    ViewData modify(OrganizationDto map) throws TException;

    /**
     * 删除机构
     * @param id
     * @return
     */
    ViewData delete(String id);

    /**
     * 查询机构
     * @param name
     * @return
     */
    ViewData find(OrganizationFindRequest name);
}
