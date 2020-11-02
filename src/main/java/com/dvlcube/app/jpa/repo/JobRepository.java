package com.dvlcube.app.jpa.repo;

import com.dvlcube.app.jpa.BasicRepository;
import com.dvlcube.app.jpa.DvlRepository;
import com.dvlcube.app.manager.data.JobBean;

public interface JobRepository extends DvlRepository<JobBean, Long>, BasicRepository<JobBean, Long> {
}
