package com.dvlcube.app.service.impl;

import com.dvlcube.app.dto.JobDTO;
import com.dvlcube.app.mapper.JobMapper;
import com.dvlcube.app.model.Job;
import com.dvlcube.app.repository.JobRepository;
import com.dvlcube.app.service.JobService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl extends GenericServiceImpl<Job, Long> implements JobService {

    @Autowired
    private JobRepository repository;

    private JobMapper mapper;

    public JobServiceImpl() {
        this.mapper = new JobMapper();
    }

    @Override
    public ResponseEntity add(JobDTO dto) {
        mapper.convertToDto(super.add(mapper.convertToEntity(dto)));
        return null;
    }

    @Override
    public ResponseEntity find(Long id) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long id) {
        return null;
    }
}
