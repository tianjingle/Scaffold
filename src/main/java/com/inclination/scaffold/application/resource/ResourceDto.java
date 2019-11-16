package com.inclination.scaffold.application.resource;


import lombok.Data;

@Data
public class ResourceDto {

	private Integer id;
	
	private String resourceName;
	
	private String resourceUrl;
	
	private String content;
	
	private String flag;
}
