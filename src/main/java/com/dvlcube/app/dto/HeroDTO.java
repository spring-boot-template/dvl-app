package com.dvlcube.app.dto;

import com.dvlcube.app.model.Faction;
import com.dvlcube.utils.BaseDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroDTO extends BaseDTO {
    @Positive
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String pic;
    @Positive
    private Integer stars;
    @NotEmpty
    private String url;
    @Valid
    private FactionDTO factionDTO;
    @Valid
    private JobDTO jobDTO;
}
