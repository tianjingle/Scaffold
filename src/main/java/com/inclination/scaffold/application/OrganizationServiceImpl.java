package com.inclination.scaffold.application;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.inclination.scaffold.api.request.organization.OrganizationFindRequest;
import com.inclination.scaffold.application.organization.OrganizationDto;
import com.inclination.scaffold.application.organization.OrganizationService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DOrganization;
import com.inclination.scaffold.infrastraction.repository.OrganizationPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.OrganizationPo;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationPoMapper organizationPoMapper;

    @Override
    public ViewData add(OrganizationDto dto) throws TException {
       return ModelMapUtils.map(dto, DOrganization.class).orgAdd(organizationPoMapper);
    }

    @Override
    public ViewData modify(OrganizationDto dto) throws TException {
        return ModelMapUtils.map(dto, DOrganization.class).orgModify(organizationPoMapper);
    }

    @Override
    public ViewData delete(String id) {
        DOrganization organization=new DOrganization();
        organization.setId(Integer.valueOf(id));
        return organization.delete(organizationPoMapper);
    }

    @Override
    public ViewData find(OrganizationFindRequest name) {
        DOrganization organization=new DOrganization();
        organization.setName(name.getName());
        Example example=new Example(OrganizationPo.class);
        if ("-1".equals(name.getName())){
            List<OrganizationPo> list=organizationPoMapper.selectAll();
            return ViewData.success(list);
        }else{
            example.createCriteria().andLike("name",name.getName());
            Page page= PageHelper.startPage(name.getStart(),name.getLimit());
            List<OrganizationPo> list=organizationPoMapper.selectByExample(example);
            return ViewData.success(list,page.getPages(),page.getTotal());
        }
    }
}
