package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.HeroMapper;
import com.dvlcube.app.model.Hero;
import com.dvlcube.app.repository.HeroRepository;
import com.dvlcube.app.service.HeroService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroServiceImpl extends GenericServiceImpl<Hero, Long> implements HeroService {

    @Autowired
    private HeroRepository repository;

    private HeroMapper mapper;

    public HeroServiceImpl(HeroMapper mapper) {
        this.mapper = mapper;
    }
}
