package com.highlander.repository;

import com.highlander.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByUserNameAndPassword(String userName, String password);

}
