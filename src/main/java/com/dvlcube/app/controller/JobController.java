package com.dvlcube.app.controller;

import com.dvlcube.app.dto.JobDTO;
import com.dvlcube.app.dto.filter.JobFilterDTO;
import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.manager.data.e.Menu;
import com.dvlcube.app.mapper.JobMapper;
import com.dvlcube.app.model.Job;
import com.dvlcube.app.service.JobService;
import com.dvlcube.utils.GenericMapper;
import com.dvlcube.utils.GenericService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@MenuItem(value = Menu.JOB)
@RequestMapping("${dvl.rest.prefix}/job")
public class JobController implements ListRest<Job, JobMapper, JobFilterDTO, JobDTO, Long> {

    private JobService service;

    @Override
    public GenericMapper<Job, JobDTO> getMapper() {
        return new JobMapper();
    }

    @Override
    public JobMapper getFilterMapper() {
        return new JobMapper();
    }

    @Override
    public GenericService<Job, Long> getService() {
        return service;
    }

    @Autowired
    public JobController(JobService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation("Create Job")
    public ResponseEntity<JobDTO> add(@ApiParam(value = "JobDTO", required = true)
                                          @Valid @RequestBody JobDTO dto){
        return service.add(dto);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find by id Job")
    public ResponseEntity<JobDTO> find(@ApiParam(value = "id", required = true) @PathVariable Long id){
        return service.find(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Job by id")
    public ResponseEntity delete(@ApiParam(value = "id", required = true) @PathVariable Long id){
        return service.delete(id);
    }
}
