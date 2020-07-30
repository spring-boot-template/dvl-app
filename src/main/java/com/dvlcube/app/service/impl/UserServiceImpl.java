package com.dvlcube.app.service.impl;

import com.dvlcube.app.mapper.UserMapper;
import com.dvlcube.app.model.User;
import com.dvlcube.app.repository.UserRepository;
import com.dvlcube.app.service.UserService;
import com.dvlcube.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository repository;

    private UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }
}
