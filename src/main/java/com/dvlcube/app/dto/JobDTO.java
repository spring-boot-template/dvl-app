package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO extends BaseDTO {
    @Positive
    private Long id;
    @NotEmpty
    private String name;
    @Positive
    private Integer max;
}
