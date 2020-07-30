package com.dvlcube.app.controller;

import com.dvlcube.utils.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Transactional
public interface ListRest<E extends BaseEntity<ID>, M extends FilterMapper<E,F>, F, D, ID>{

    @GetMapping("/getAll")
    @ApiOperation(value = "ListAll")
    @DefaultParamsPagedList
    default Page<D> getAll(Integer page, Integer count, String order, String sortProperty, F filterDTO){

        E object = getFilterMapper().convertFilterToEntity(filterDTO);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<E> example = Example.of(object, matcher);

        PageRequest pageRequest = PagingUtils.createPageRequest(page, count, order, sortProperty);

        Page<E> pages = getService().getAllPaginated(example,pageRequest);

        return getMapper().convertToSliceDto(pages);
    }

    GenericMapper<E,D> getMapper();

    M getFilterMapper();

    GenericService<E,ID> getService();
}
