package com.dvlcube.app.mapper;

import com.dvlcube.app.dto.UserDTO;
import com.dvlcube.app.model.User;
import com.dvlcube.utils.FilterMapper;
import com.dvlcube.utils.GenericMapper;

public class UserMapper implements GenericMapper<User, UserDTO>, FilterMapper<User, UserDTO> {
    @Override
    public User convertFilterToEntity(UserDTO filterDTO) {
        return User.builder().build();
    }

    @Override
    public User convertToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .name(dto.getName())
                .pic(dto.getPic())
                .password(dto.getPassword())
                .verified(dto.getVerified())
                .lastActivity(dto.getLastActivity())
                .requests(dto.getRequests())
                .build();
    }

    @Override
    public UserDTO convertToDto(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .pic(entity.getPic())
                .password(entity.getPassword())
                .verified(entity.getVerified())
                .lastActivity(entity.getLastActivity())
                .requests(entity.getRequests())
                .build();
    }
}
