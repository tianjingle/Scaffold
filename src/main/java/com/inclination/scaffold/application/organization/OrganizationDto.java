package com.inclination.scaffold.application.organization;

import lombok.Data;

@Data
public class OrganizationDto {

    private Integer id;
    /**
     * 机构名称
     */
    private String name;

    private String content;
}
