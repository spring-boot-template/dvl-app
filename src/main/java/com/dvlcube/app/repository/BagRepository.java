package com.dvlcube.app.repository;

import com.dvlcube.app.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<Bag, Long> {
}
