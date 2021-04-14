package com.dvlcube.utils;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<E extends BaseEntity, D> {
    E convertToEntity(D dto);
    D convertToDto(E entity);
    default Page<D> convertToSliceDto(Page<E> page){ return page.map(this::convertToDto);}

    default List<E> convertToListEntity(List<D> dtos){return dtos.stream()
                                                            .map(this::convertToEntity)
                                                            .collect(Collectors.toList());}
    default List<D> covertToListDto(List<E> entitys){return entitys.stream()
                                                            .map(this::convertToDto)
                                                            .collect(Collectors.toList());}
}
