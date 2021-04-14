package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.BagHeroMapper;
import com.dvlcube.app.model.BagHero;
import com.dvlcube.app.repository.BagHeroRepository;
import com.dvlcube.app.service.BagHeroService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagHeroServiceImpl extends GenericServiceImpl<BagHero, Long> implements BagHeroService {
    @Autowired
    private BagHeroRepository repository;

    private BagHeroMapper mapper;

    public BagHeroServiceImpl() {
        this.mapper = new BagHeroMapper();
    }
}
