package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import com.dvlcube.utils.interfaces.MxBean;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class SkillDTO extends BaseDTO implements MxBean<Serializable> {
    @Positive
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String pic;
}
