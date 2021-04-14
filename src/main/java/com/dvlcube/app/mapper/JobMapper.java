package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.JobDTO;
import com.dvlcube.app.dto.filter.JobFilterDTO;
import com.dvlcube.app.model.Job;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class JobMapper implements GenericMapper<Job, JobDTO>, FilterMapper<Job, JobFilterDTO> {
    @Override
    public Job convertFilterToEntity(JobFilterDTO filterDTO) {
        return Job.builder().build();
    }

    @Override
    public Job convertToEntity(JobDTO dto) {
        return Job.builder()
                .id(dto.getId())
                .name(dto.getName())
                .max(dto.getMax())
                .build();
    }

    @Override
    public JobDTO convertToDto(Job entity) {
        return JobDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .max(entity.getMax())
                .build();
    }
}
