package com.inclination.scaffold.application.users;


public class UserDto {

	private int id;
	
	private String username;
	
	private String loginid;
	
	private String userpassword;
	
	private String useremil;

    private Integer roid;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public Integer getRoid() {
		return roid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUseremil() {
		return useremil;
	}

	public void setUseremil(String useremil) {
		this.useremil = useremil;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}


	
}
