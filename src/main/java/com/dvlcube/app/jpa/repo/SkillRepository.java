package com.dvlcube.app.jpa.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dvlcube.app.manager.data.SkillBean;
import com.dvlcube.app.jpa.BasicRepository;
import com.dvlcube.app.jpa.DvlRepository;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@Repository
public interface SkillRepository extends DvlRepository<SkillBean, Long>, BasicRepository<SkillBean, Long> {
	
	@Query("SELECT K FROM SkillBean K ORDER BY K.name ASC")
	Iterable<SkillBean> findAllOrderByNome();


}
