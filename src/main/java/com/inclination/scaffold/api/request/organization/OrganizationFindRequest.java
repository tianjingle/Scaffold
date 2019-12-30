package com.inclination.scaffold.api.request.organization;

import com.inclination.scaffold.utils.BaseQueryRequestEntity;
import lombok.Data;

@Data
public class OrganizationFindRequest extends BaseQueryRequestEntity {

    private String name;
}
