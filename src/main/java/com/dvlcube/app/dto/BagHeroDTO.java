package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BagHeroDTO extends BaseDTO {
    private Long id;
    private int amount;
}
