package com.inclination.scaffold.application.role;

import lombok.Data;

@Data
public class RoleDto {
	
	private Integer id;

	private String roleName;

	private String menuId;

	private String content;

	private String flag;
}
