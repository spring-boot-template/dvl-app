package com.dvlcube.app.jpa.repo;

import org.springframework.stereotype.Repository;

import com.dvlcube.app.manager.data.SkillBean;
import com.dvlcube.app.jpa.BasicRepository;
import com.dvlcube.app.jpa.DvlRepository;

import java.util.Optional;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@Repository
public interface SkillRepository extends DvlRepository<SkillBean, Long>, BasicRepository<SkillBean, Long> {
    Iterable<SkillBean> findAllByOrderByName();
    Iterable<SkillBean> findAllByNameLike(String name);
    Optional<SkillBean> findByName(String name);
}
