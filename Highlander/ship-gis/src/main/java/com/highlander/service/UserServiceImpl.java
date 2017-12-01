package com.highlander.service;

import com.highlander.entity.User;
import com.highlander.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserAccount(String account) {
        User user = new User();
        user.setUserName(account);
        Example<User> ex = Example.of(user);
        return userRepository.findOne(ex);
    }

    @Override
    public User findByUserAccountPassword(String account, String password) {
        return userRepository.findByUserNameAndPassword(account, password);
    }

    @Override
    public Set<String> findRoles(User user) {

        return null;
    }

    @Override
    public Set<String> findPermissions(User user) {
        Set<String> set = new HashSet<>();
        set.add("user:listUsers");
        return set;
    }

}
