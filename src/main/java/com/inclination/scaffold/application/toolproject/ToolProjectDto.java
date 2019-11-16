package com.inclination.scaffold.application.toolproject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ToolProjectDto {

	private Integer id;
	
	private String name;

	private String url;

	private String userName;
	
	private String userPassword;
	
}
