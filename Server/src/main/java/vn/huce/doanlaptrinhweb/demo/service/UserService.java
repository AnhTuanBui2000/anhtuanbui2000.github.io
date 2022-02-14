/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.huce.doanlaptrinhweb.demo.model.User;
import vn.huce.doanlaptrinhweb.demo.repository.UserRepository;

/**
 *
 * @author tuank
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
        
    }
    public Collection<User> findUserById(Long id){
        return userRepo.findUserById(id);
    }
    public void updateUser(Long id,User user){
        user.setId(id);
        userRepo.save(user);
    }
    public User findOneById(Long id){
        return userRepo.findOneById(id);
    }
}
