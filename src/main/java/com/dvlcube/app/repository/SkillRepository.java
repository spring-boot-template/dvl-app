package com.dvlcube.app.repository;

import org.springframework.stereotype.Repository;

import com.dvlcube.app.model.Skill;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@Repository
public interface SkillRepository extends DvlRepository<Skill, Long>, BasicRepository<Skill, Long> {
}
