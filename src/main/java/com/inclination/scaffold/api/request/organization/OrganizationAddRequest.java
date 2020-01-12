package com.inclination.scaffold.api.request.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class OrganizationAddRequest {
    /**
     * 机构名称
     */
    @ApiModelProperty(name = "name")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(name = "内容")
    private String content;
}
