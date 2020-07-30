package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SkillDTO extends BaseDTO {
    private Long id;
    private String name;
    private String description;
    private String pic;
}
