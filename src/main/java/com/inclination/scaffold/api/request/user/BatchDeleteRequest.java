package com.inclination.scaffold.api.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class BatchDeleteRequest {

    @ApiModelProperty(name = "userId",value = "用戶的id")
    public List<String> userIds;
}
