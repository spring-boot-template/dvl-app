package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.BagDTO;
import com.dvlcube.app.model.Bag;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class BagMapper implements GenericMapper<Bag, BagDTO>, FilterMapper<Bag, BagDTO> {

    @Override
    public Bag convertFilterToEntity(BagDTO filterDTO) {
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
                .heroList(new BagHeroMapper().convertToListEntity(dto.getBagHeroDTOs()))
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
                .bagHeroDTOs(new BagHeroMapper().covertToListDto(entity.getHeroList()))
                .build();
    }
}
