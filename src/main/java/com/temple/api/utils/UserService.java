package com.temple.api.utils;

import com.temple.api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserUtils userUtils;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserUtils userUtils, UserRepository userRepository) {
        this.userUtils = userUtils;
        this.userRepository = userRepository;
        this.userUtils.setUserRepository(userRepository);
    }

}

