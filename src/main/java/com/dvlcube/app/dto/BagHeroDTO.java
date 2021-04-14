package com.dvlcube.app.dto;

import com.dvlcube.app.model.Hero;
import com.dvlcube.app.model.User;
import com.dvlcube.utils.BaseDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BagHeroDTO extends BaseDTO {
    @Positive
    private Long id;

    @Positive
    private Integer amount;

    @Valid
    private UserDTO ownerDTO;

    @Valid
    private HeroDTO heroDTO;

    @Valid
    private BagDTO bagDTO;

    @Valid
    private FusionDTO fusionDTO;
}
