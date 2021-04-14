package com.dvlcube.app.repository;

import com.dvlcube.app.model.Faction;
import com.dvlcube.app.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
