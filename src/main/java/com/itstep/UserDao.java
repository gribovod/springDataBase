package com.itstep;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 * @author student
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long>{
    public User findByName(String name);
}
