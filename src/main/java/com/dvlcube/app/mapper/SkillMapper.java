package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.HeroDTO;
import com.dvlcube.app.dto.SkillDTO;
import com.dvlcube.app.model.Hero;
import com.dvlcube.app.model.Skill;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class SkillMapper implements GenericMapper<Skill, SkillDTO>, FilterMapper<Skill, SkillDTO> {
    @Override
    public Skill convertFilterToEntity(SkillDTO filterDTO) {
        return Skill.builder().build();
    }

    @Override
    public Skill convertToEntity(SkillDTO dto) {
        return Skill.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .pic(dto.getPic())
                .build();
    }

    @Override
    public SkillDTO convertToDto(Skill entity) {
        return SkillDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .pic(entity.getPic())
                .build();
    }
}
