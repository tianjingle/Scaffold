package com.inclination.scaffold.infrastraction.otherSystem.git.vo;

import com.inclination.scaffold.infrastraction.otherSystem.git.vo.GitUserDto;
import lombok.Data;

import java.util.List;

@Data
public class GitUserView {

    private List<GitUserDto> data;

}
