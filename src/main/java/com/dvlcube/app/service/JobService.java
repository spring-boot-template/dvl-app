package com.dvlcube.app.service;

import com.dvlcube.app.dto.JobDTO;
import com.dvlcube.app.model.Job;
import com.dvlcube.utils.GenericService;
import org.springframework.http.ResponseEntity;

public interface JobService extends GenericService<Job,Long> {
    ResponseEntity add(JobDTO dto);
    ResponseEntity find(Long id);
    ResponseEntity delete(Long id);
}
