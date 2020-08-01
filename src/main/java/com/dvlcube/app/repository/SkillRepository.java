package com.dvlcube.app.repository;

import org.springframework.stereotype.Repository;

import com.dvlcube.app.model.SkillBean;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@Repository
public interface SkillRepository extends DvlRepository<SkillBean, Long>, BasicRepository<SkillBean, Long> {
}
