package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.FusionDTO;
import com.dvlcube.app.dto.HeroDTO;
import com.dvlcube.app.model.Fusion;
import com.dvlcube.app.model.Hero;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class HeroMapper implements GenericMapper<Hero, HeroDTO>, FilterMapper<Hero, HeroDTO> {
    @Override
    public Hero convertFilterToEntity(HeroDTO filterDTO) {
        return Hero.builder().build();
    }

    @Override
    public Hero convertToEntity(HeroDTO dto) {
        return Hero.builder()
                .id(dto.getId())
                .name(dto.getName())
                .pic(dto.getPic())
                .stars(dto.getStars())
                .url(dto.getUrl())
                .build();
    }

    @Override
    public HeroDTO convertToDto(Hero entity) {
        return HeroDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pic(entity.getPic())
                .stars(entity.getStars())
                .url(entity.getUrl())
                .build();
    }
}
