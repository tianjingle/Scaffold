package com.inclination.scaffold.api.interfaces;


import com.inclination.scaffold.api.request.organization.OrganizationAddRequest;
import com.inclination.scaffold.api.request.organization.OrganizationFindRequest;
import com.inclination.scaffold.api.request.organization.OrganizationModifyRequest;
import com.inclination.scaffold.application.organization.OrganizationDto;
import com.inclination.scaffold.application.organization.OrganizationService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/org")
public class OrganizationApi {

    /**
     * 添加机构
     */
    @Autowired
    private OrganizationService organizationService;


    @RequestMapping(value = "/add")
    @ApiOperation(value = "")
    public ViewData addOrg(@Valid @RequestBody OrganizationAddRequest request) throws TException {
        return organizationService.add(ModelMapUtils.map(request, OrganizationDto.class));
    }

    @RequestMapping(value = "/modify")
    @ApiOperation(value = "")
    public ViewData modifyOrg(@Valid @RequestBody OrganizationModifyRequest request) throws TException {
        return organizationService.modify(ModelMapUtils.map(request, OrganizationDto.class));
    }

    @RequestMapping(value = "/delete/{id}")
    @ApiOperation(value = "")
    public ViewData deleteOrg(@PathVariable("id") String id){
        return organizationService.delete(id);
    }

    @RequestMapping(value = "/find")
    @ApiOperation(value = "")
    public ViewData findOrgs(@ModelAttribute OrganizationFindRequest name){
        return organizationService.find(name);
    }
}
