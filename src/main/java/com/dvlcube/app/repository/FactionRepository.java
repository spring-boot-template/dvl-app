package com.dvlcube.app.repository;

import com.dvlcube.app.model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionRepository extends JpaRepository<Faction, Long> {
}
