package com.dvlcube.utils;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class GenericServiceImpl<E extends BaseEntity<ID>, ID extends Serializable> implements GenericService<E,ID> {

    @Autowired
    private JpaRepository<E,ID> repository;

    @Override
    public ResponseEntity<List<E>> saveOrUpdateList(Iterable<E> iterables) {
        List<E> list = repository.saveAll(iterables);
        return ResponseEntity.ok(list);
    }

    @Override
    public Page<E> getAllPaginated(Example<E> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public E get(ID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public E add(E entity) {
        return saveOrUpdate(entity);
    }

    @Override
    public E update(E entity) {
        return saveOrUpdate(entity);
    }

    @Transactional
    public E saveOrUpdate(E entity) {
        try {
            return repository.save(entity);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void removeEntities(Iterable<E> entities) {
        entities.forEach(e -> {
            removeById(e.getId());
        });
    }

    @Override
    public void removeEntitiesById(Iterable<ID> entityIds) {
        entityIds.forEach(id -> removeById(id));
    }
}
