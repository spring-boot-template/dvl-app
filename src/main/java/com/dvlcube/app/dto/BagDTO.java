package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BagDTO extends BaseDTO {
    private Long id;
    private String name;
    private String pic;
    private Boolean share;
    private String url;
    private UserDTO ownerDTO;
    private List<BagHeroDTO> bagHeroDTOs;
}
