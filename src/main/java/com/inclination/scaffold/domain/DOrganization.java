package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.OrganizationPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.OrganizationPo;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import lombok.Data;
import tk.mybatis.mapper.entity.Example;

@Data
public class DOrganization {

    private Integer id;
    /**
     * 机构名称
     */
    private String name;

    private String content;

    public ViewData orgAdd(OrganizationPoMapper organizationPoMapper) throws TException {
        Example example=new Example(OrganizationPo.class);
        example.createCriteria().andEqualTo("name",this.name);
        long count=organizationPoMapper.selectCountByExample(example);
        if (count>0){
            throw new TException(TErrorCode.ERROR_ADD_ORG_CODE,TErrorCode.ERROR_ADD_ORG_MSG);
        }else{
            if (organizationPoMapper.insert(ModelMapUtils.map(this,OrganizationPo.class))==1){
                return ViewData.success(true);
            }
        }
        return ViewData.error(TErrorCode.ERROR_ADD_ORG_MSG);
    }

    public ViewData orgModify(OrganizationPoMapper organizationPoMapper) throws TException {
        Example example=new Example(OrganizationPo.class);
        example.createCriteria().andEqualTo("name",this.name);
        long count=organizationPoMapper.selectCountByExample(example);
        if (count>1){
            throw new TException(TErrorCode.ERROR_UPDATE_ORG_CODE,TErrorCode.ERROR_UPDATE_ORG_MSG);
        }else{
            if (organizationPoMapper.updateByPrimaryKey(ModelMapUtils.map(this, OrganizationPo.class))==1){
                return ViewData.success(true);
            }else{
                return ViewData.error(TErrorCode.ERROR_UPDATE_ORG_MSG);
            }
        }
    }

    public ViewData delete(OrganizationPoMapper organization) {
        if (organization.deleteByPrimaryKey(this.id)==1){
            return ViewData.success(true);
        }else{
            return ViewData.error(TErrorCode.ERROR_DELETE_ORG_MSG);
        }
    }
}


