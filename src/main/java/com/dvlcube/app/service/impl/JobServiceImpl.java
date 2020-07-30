package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.JobMapper;
import com.dvlcube.app.model.Job;
import com.dvlcube.app.repository.JobRepository;
import com.dvlcube.app.service.JobService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl extends GenericServiceImpl<Job, Long> implements JobService {

    @Autowired
    private JobRepository repository;

    private JobMapper mapper;

    public JobServiceImpl(JobMapper mapper) {
        this.mapper = mapper;
    }
}
