package com.dvlcube.utils;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GenericService<E extends BaseEntity<ID>, ID > {

    ResponseEntity<List<E>> saveOrUpdateList(Iterable<E> iterables);

    E update(E entity);

    E add(E entity);

    Page<E> getAllPaginated(Example<E> example, Pageable pageable);

    Page<E> getAllPaginated(Pageable pageable);

    E get(ID id);

    void removeById(ID id);

    void removeEntities(Iterable<E> entities);

    void removeEntitiesById(Iterable<ID> entityIds);
}
