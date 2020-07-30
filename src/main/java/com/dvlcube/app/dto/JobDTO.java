package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
public class JobDTO extends BaseDTO {
    @Positive
    private Long id;
    @NotEmpty
    private String name;
    @Positive
    private Integer max;
}
