package com.dvlcube.app.rest;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.manager.data.JobBean;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.app.services.JobService;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;

@RestController
@MenuItem(value = CONFIGURATION)
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobRest implements MxFilterableBeanService<JobBean, Long> {
    @Autowired
    private JobService jobService;

    @Override
    @GetMapping
    public Iterable<JobBean> get(@RequestParam Map<String, String> params) {
        return jobService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<JobBean> get(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @Override
    @PostMapping
    public MxRestResponse post(@Valid @RequestBody JobBean body) {
        JobBean save = jobService.save(body);
        return GenericRestResponse.ok(save.getId());
    }
    @GetMapping("/filtered")
    public List<JobBean> getFiltered(@RequestParam Map<String, String> params) {
        return this.jobService.findAllBy(params);
    }


    @GetMapping("/group/{group}/filtered")
    public List<JobBean> getGroupFiltered(@PathVariable String group, @RequestParam Map<String, String> params) {
        return this.jobService.findAllBy(params, group);
    }

    @Override
    public Iterable<JobBean> getLike(String id) {
        return this.jobService.getLike(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.jobService.deleteById(id);
    }
}
