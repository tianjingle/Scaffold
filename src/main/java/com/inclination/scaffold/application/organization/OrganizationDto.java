package com.inclination.scaffold.application.organization;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationDto {

    private Integer id;
    /**
     * 机构名称
     */
    private String name;

    private String content;
}
