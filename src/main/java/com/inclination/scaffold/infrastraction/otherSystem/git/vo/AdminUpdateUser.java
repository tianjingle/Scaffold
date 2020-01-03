package com.inclination.scaffold.infrastraction.otherSystem.git.vo;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class AdminUpdateUser {


    private boolean active;

    private boolean admin;

    private boolean allow_create_organization;

    private boolean allow_git_hook;

    private boolean allow_import_local;

    private String email;

    private String full_name;

    private String location;

    private String login_name;

    private int max_repo_creation;
    private boolean must_change_password;

    private String password;

    private boolean prohibit_login;

    private int source_id;

    private String website;
}
