package com.dvlcube.app.services;

import com.dvlcube.app.jpa.repo.SkillRepository;
import com.dvlcube.app.manager.data.SkillBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public Iterable<SkillBean> findAll(){
        return this.skillRepository.findAllByOrderByName();
    }

    public Optional<SkillBean> findById(Long id) {
        return this.skillRepository.findById(id);
    }

    public SkillBean save(SkillBean skillBean) {
        return this.skillRepository.save(skillBean);
    }

    public List<SkillBean> findAllBy(Map<String, String> params){
        return this.skillRepository.findAllBy(params);
    }

    public List<SkillBean> findAllBy(Map<String, String> params, String group){
        return this.skillRepository.findAllBy(params, group);
    }

    public Iterable<SkillBean> findAllLike(String id){
        return this.skillRepository.findAllLike(id);
    }

    public void deleteById(Long id){
        this.skillRepository.deleteById(id);
    }
}
