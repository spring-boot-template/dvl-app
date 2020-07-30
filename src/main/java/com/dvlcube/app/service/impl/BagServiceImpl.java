package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.BagMapper;
import com.dvlcube.app.model.Bag;
import com.dvlcube.app.repository.BagRepository;
import com.dvlcube.app.service.BagService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagServiceImpl extends GenericServiceImpl<Bag,Long> implements BagService {

    @Autowired
    private BagRepository repository;

    private BagMapper mapper;

    public BagServiceImpl(BagMapper mapper) {
        this.mapper = mapper;
    }
}
