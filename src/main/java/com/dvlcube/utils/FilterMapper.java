package com.dvlcube.utils;

public interface FilterMapper<E extends BaseEntity, F> {
    E convertFilterToEntity(F filterDTO);
}
