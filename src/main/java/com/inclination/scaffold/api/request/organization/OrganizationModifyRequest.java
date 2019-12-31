package com.inclination.scaffold.api.request.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class OrganizationModifyRequest {

    @ApiModelProperty(name = "id")
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 机构名称
     */
    @ApiModelProperty(name = "name")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(name = "内容")
    private String content;
}
