package com.better.xing.web.jpa.dao;

import com.better.xing.web.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 14:45
 */
public interface UserRepository extends JpaRepository<User,String> {
    public User findUserByMobile(String mobile);
}
