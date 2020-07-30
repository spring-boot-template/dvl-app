package com.dvlcube.utils;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericService<E extends BaseEntity<ID>, ID > {

    ResponseEntity<List<E>> saveOrUpdateList(Iterable<E> iterables);

    ResponseEntity<E> update(E entity);

    ResponseEntity<E> add(E entity);

    Page<E> getAllPaginated(Example<E> example, Pageable pageable);

    ResponseEntity<E> get(ID id);

    ResponseEntity<E> removeById(ID id);

    void removeEntities(Iterable<E> entities);

    void removeEntitiesById(Iterable<ID> entityIds);
}
