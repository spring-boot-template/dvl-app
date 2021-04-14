package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.FusionDTO;
import com.dvlcube.app.dto.filter.FusionFilterDTO;
import com.dvlcube.app.model.Fusion;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class FusionMapper implements GenericMapper<Fusion, FusionDTO>, FilterMapper<Fusion, FusionFilterDTO> {
    @Override
    public Fusion convertFilterToEntity(FusionFilterDTO filterDTO) {
        return Fusion.builder().build();
    }

    @Override
    public Fusion convertToEntity(FusionDTO dto) {
        return Fusion.builder()
                .id(dto.getId())
                .name(dto.getName())
                .pic(dto.getPic())
                .share(dto.getShare())
                .url(dto.getUrl())
                .build();
    }

    @Override
    public FusionDTO convertToDto(Fusion entity) {
        return FusionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pic(entity.getPic())
                .share(entity.getShare())
                .url(entity.getUrl())
                .build();
    }
}
