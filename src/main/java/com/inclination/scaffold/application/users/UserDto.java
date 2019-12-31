package com.inclination.scaffold.application.users;


import lombok.Data;

@Data
public class UserDto {

	private int id;
	
	private String userName;
	
	private String loginId;
	
	private String userPassword;
	
	private String userEmil;

    private Integer roId;

	private int orgId;

	private String orgName;
}
