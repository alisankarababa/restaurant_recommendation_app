package com.tech.user_service.service;

import com.tech.user_service.entity.User;
import com.tech.user_service.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseEntityServiceImpl<User, IUserRepository> implements IUserService{

    @Autowired
    public UserServiceImpl(IUserRepository repository) {
        super(repository);
    }

}
