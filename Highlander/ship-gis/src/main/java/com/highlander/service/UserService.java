package com.highlander.service;

import com.highlander.entity.User;

import java.util.Set;

public interface UserService extends BaseService<User> {

    User findByUserAccount(String userName);

    User findByUserAccountPassword(String account, String password);

    Set<String> findRoles(User account);

    Set<String> findPermissions(User user);


}
