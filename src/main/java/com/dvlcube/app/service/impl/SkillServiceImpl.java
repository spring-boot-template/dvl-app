package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.SkillMapper;
import com.dvlcube.app.model.Skill;
import com.dvlcube.app.repository.SkillRepository;
import com.dvlcube.app.service.SkillService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl extends GenericServiceImpl<Skill, Long> implements SkillService {

    @Autowired
    private SkillRepository repository;

    private SkillMapper mapper;

    public SkillServiceImpl(SkillMapper mapper) {
        this.mapper = mapper;
    }
}
