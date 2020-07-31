package com.dvlcube.app.service.impl;

import com.dvlcube.app.dto.SkillDTO;
import com.dvlcube.app.mapper.SkillMapper;
import com.dvlcube.app.model.Skill;
import com.dvlcube.app.repository.SkillRepository;
import com.dvlcube.app.service.SkillService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SkillServiceImpl extends GenericServiceImpl<Skill, Long> implements SkillService {

    @Autowired
    private SkillRepository repository;

    private SkillMapper mapper;

    public SkillServiceImpl() {
        this.mapper = new SkillMapper();
    }

    @Override
    public Iterable<SkillDTO> firstPage() {
        return mapper.covertToListDto(repository.firstPage());
    }

    @Override
    public ResponseEntity<SkillDTO> findById(Long id) {
        SkillDTO dto = mapper.convertToDto(super.get(id));
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<SkillDTO> add(SkillDTO dto) {
        SkillDTO novoDto = mapper.convertToDto(super.add(mapper.convertToEntity(dto)));
        return ResponseEntity.ok(novoDto);
    }

    @Override
    public ResponseEntity<List<SkillDTO>> findAllBy(Map<String, String> params) {
        List<Skill> skills = repository.findAllBy(params);
        return ResponseEntity.ok(mapper.covertToListDto(skills));
    }

    @Override
    public ResponseEntity<List<SkillDTO>> findAllBy(Map<String, String> params, String group) {
        List<Skill> skills = repository.findAllBy(params, group);
        return ResponseEntity.ok(mapper.covertToListDto(skills));
    }

    @Override
    public ResponseEntity<Iterable<SkillDTO>> findAllLike(String id) {
        List<Skill> skills = repository.findAllLike(id);
        return ResponseEntity.ok(mapper.covertToListDto(skills));
    }

    @Override
    public ResponseEntity findByName(String name) {
        Optional<Skill> op = repository.findByNameLike(name);
        if(!op.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.convertToDto(op.get()));
    }

    @Override
    public ResponseEntity<Boolean> findByNameReturnBool(String name) {
        Optional<Skill> op = repository.findByNameLike(name);
        return ResponseEntity.ok(op.isPresent());
    }

    @Override
    public void delete(Long id) {
        super.removeById(id);
    }
}
