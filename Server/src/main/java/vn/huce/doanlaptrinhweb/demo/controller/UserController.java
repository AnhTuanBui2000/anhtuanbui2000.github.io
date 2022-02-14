/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.huce.doanlaptrinhweb.demo.model.Friends;
import vn.huce.doanlaptrinhweb.demo.model.GroupChat;

import vn.huce.doanlaptrinhweb.demo.model.User;
import vn.huce.doanlaptrinhweb.demo.service.FriendsService;
import vn.huce.doanlaptrinhweb.demo.service.GroupChatService;

import vn.huce.doanlaptrinhweb.demo.service.UserService;

/**
 *
 * @author tuank
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    GroupChatService grService;
    @Autowired
    FriendsService frService;
    @GetMapping("/profile/{id}")
    public User getUser (@PathVariable Long id){
        User user=userService.findOneById(id);
        return user;
    }
    
    @GetMapping("/find/{id}")
    public Collection<User> findUser (@PathVariable Long id){
        return userService.findUserById(id);
    }
    
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id ,@RequestBody User user){
        userService.updateUser(id, user);    
        ArrayList<GroupChat> findname=grService.findUser(user.getId()); 
        findname.forEach(name->name.setNameuser(user.getName()));
        findname.forEach(name->grService.updateGroup(name.getId(),name));
        ArrayList<Friends> friends=frService.findListFriendsByIdFriend(id);
        friends.forEach(friend->friend.setName(user.getName())); 
        friends.forEach(friend->frService.updateFriend(friend.getId(),friend));
        return new ResponseEntity<>(null,HttpStatus.valueOf(303)); 
    }
}
