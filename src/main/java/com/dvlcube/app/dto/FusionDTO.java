package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FusionDTO extends BaseDTO {
    private Long id;
    private String name;
    private String pic;
    private Boolean share;
    private String url;
}
