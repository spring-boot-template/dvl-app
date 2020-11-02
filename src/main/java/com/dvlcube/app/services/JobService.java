package com.dvlcube.app.services;

import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Iterable<JobBean> findAll() {
        return this.jobRepository.findAll();
    }

    public Optional<JobBean> findById(Long id) {
        return this.jobRepository.findById(id);
    }

    @Transactional
    public JobBean save(JobBean jobBean) {
        JobBean job = new JobBean();
        if(jobBean.getId() != null) {
            job = this.findById(jobBean.getId()).get();
        }
        BeanUtils.copyProperties(jobBean, job, JobBean.ignoreProperties);
        return this.jobRepository.save(jobBean);
    }

    public List<JobBean> findAllBy(Map<String, String> params) {
        return this.jobRepository.findAllBy(params);
    }

    public List<JobBean> findAllBy(Map<String, String> params, String group) {
        return this.jobRepository.findAllBy(params, group);
    }

    public Iterable<JobBean> getLike(String id) {
        return this.jobRepository.findAllLike(id);
    }

    public void deleteById(Long id) {
        this.jobRepository.deleteById(id);
    }

}
