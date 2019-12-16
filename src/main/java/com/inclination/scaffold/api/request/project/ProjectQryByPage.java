package com.inclination.scaffold.api.request.project;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询项目资料")
public class ProjectQryByPage extends BaseQueryRequestEntity {

    @ApiModelProperty(name = "artifactid",value = "项目名称",notes = "资料")
    private String artifactId;


}
