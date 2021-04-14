package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.BagDTO;
import com.dvlcube.app.dto.filter.BagFilterDTO;
import com.dvlcube.app.model.Bag;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class BagMapper implements GenericMapper<Bag, BagDTO>, FilterMapper<Bag, BagFilterDTO> {

    @Override
    public Bag convertFilterToEntity(BagFilterDTO filterDTO) {
        return Bag.builder().build();
    }

    @Override
    public Bag convertToEntity(BagDTO dto) {
        return Bag.builder()
                .id(dto.getId())
                .name(dto.getName())
                .pic(dto.getPic())
                .share(dto.getShare())
                .url(dto.getUrl())
                .owner(new UserMapper().convertToEntity(dto.getOwnerDTO()))
                .build();
    }

    @Override
    public BagDTO convertToDto(Bag entity) {
        return BagDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pic(entity.getPic())
                .share(entity.getShare())
                .url(entity.getUrl())
                .ownerDTO(new UserMapper().convertToDto(entity.getOwner()))
                .build();
    }
}
