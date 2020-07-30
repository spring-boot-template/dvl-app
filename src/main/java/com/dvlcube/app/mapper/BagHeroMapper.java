package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.BagDTO;
import com.dvlcube.app.dto.BagHeroDTO;
import com.dvlcube.app.model.Bag;
import com.dvlcube.app.model.BagHero;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class BagHeroMapper implements GenericMapper<BagHero, BagHeroDTO>, FilterMapper<BagHero, BagHeroDTO> {
    @Override
    public BagHero convertFilterToEntity(BagHeroDTO filterDTO) {
        return BagHero.builder().build();
    }

    @Override
    public BagHero convertToEntity(BagHeroDTO dto) {
        return BagHero.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .build();
    }

    @Override
    public BagHeroDTO convertToDto(BagHero entity) {
        return BagHeroDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .build();
    }
}
