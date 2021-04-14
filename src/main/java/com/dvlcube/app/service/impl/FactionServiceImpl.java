package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.FactionMapper;
import com.dvlcube.app.model.Faction;
import com.dvlcube.app.repository.FactionRepository;
import com.dvlcube.app.service.FactionService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactionServiceImpl extends GenericServiceImpl<Faction, Long> implements FactionService {

    @Autowired
    private FactionRepository repository;

    private FactionMapper mapper;

    public FactionServiceImpl() {
        this.mapper = new FactionMapper();
    }
}
