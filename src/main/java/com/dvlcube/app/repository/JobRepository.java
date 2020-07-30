package com.dvlcube.app.repository;

import com.dvlcube.app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
