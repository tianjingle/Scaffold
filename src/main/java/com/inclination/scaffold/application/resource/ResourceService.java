package com.inclination.scaffold.application.resource;

import com.inclination.scaffold.api.request.resource.ResourceQryByPage;
import com.inclination.scaffold.api.response.resource.ResourceManagerQryResponse;
import com.inclination.scaffold.constant.exception.TException;

public interface ResourceService {

	void addResource(ResourceDto map) throws TException;

	void modifyResource(ResourceDto map) throws TException;

	void deleteResource(ResourceDto dto) throws TException;

	ResourceManagerQryResponse resourceQryByPages(ResourceQryByPage request);

}
