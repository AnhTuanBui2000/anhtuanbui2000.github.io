/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.huce.doanlaptrinhweb.demo.model.User;

/**
 *
 * @author tuank
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where username=:username")
    public User findByUsername(@Param("username")String username);
    @Query("select u from User u where id=:id")
    public Collection<User> findUserById(@Param("id")Long id);
    @Query("select u from User u where id=:id")
    public User findOneById(@Param("id")Long id);
    
}
