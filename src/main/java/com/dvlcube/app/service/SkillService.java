package com.dvlcube.app.service;

import com.dvlcube.app.dto.SkillDTO;
import com.dvlcube.app.model.Skill;
import com.dvlcube.utils.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SkillService extends GenericService<Skill, Long> {
    Iterable<SkillDTO> firstPage();
    ResponseEntity<SkillDTO> findById(Long id);
    ResponseEntity<SkillDTO> add(SkillDTO dto);
    ResponseEntity<List<SkillDTO>> findAllBy(Map<String,String> params);
    ResponseEntity<List<SkillDTO>> findAllBy(Map<String,String> params, String group);
    ResponseEntity<Iterable<SkillDTO>> findAllLike(String id);
    void delete(Long id);
}
