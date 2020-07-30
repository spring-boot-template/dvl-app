package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.FactionDTO;
import com.dvlcube.app.model.Faction;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class FactionMapper implements GenericMapper<Faction, FactionDTO>, FilterMapper<Faction, FactionDTO> {
    @Override
    public Faction convertFilterToEntity(FactionDTO filterDTO) {
        return Faction.builder().build();
    }

    @Override
    public Faction convertToEntity(FactionDTO dto) {
        return Faction.builder()
                .id(dto.getId())
                .name(dto.getName())
                .pic(dto.getPic())
                .url(dto.getUrl())
                .build();
    }

    @Override
    public FactionDTO convertToDto(Faction entity) {
        return FactionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pic(entity.getPic())
                .url(entity.getUrl())
                .build();
    }
}
