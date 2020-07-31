package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FusionDTO extends BaseDTO {
    @Positive
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String pic;
    @NotNull
    private Boolean share;
    @NotEmpty
    private String url;
    @Valid
    private UserDTO ownerDto;
}
