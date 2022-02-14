/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class FriendsController {
    @Autowired
    UserService userService;
    @Autowired
    FriendsService friendsService;
    @Autowired
    GroupChatService grService;
    @GetMapping("/friends/{id}")
    public Collection<Friends> getListFriends(@PathVariable Long id){
        
        return friendsService.findListFriendsById(id);

    }
    @DeleteMapping("/delete/{id_me}/{id_friend}")
    public ResponseEntity<?> deleteFriend(@PathVariable Long id_me,@PathVariable Long id_friend){
        friendsService.deleteFriend(id_friend,id_me);
        return new ResponseEntity<>(null,HttpStatus.valueOf(204));  
    }
    @PostMapping("/add/friend")
    public ResponseEntity<?> addFriend(@RequestBody Friends friend){
        friendsService.addFriend(friend);
        return new ResponseEntity<>(null,HttpStatus.valueOf(200));
    }
    @PutMapping("/update/statustrue/{id}")
    public ResponseEntity<?> updateStatusTrue(@PathVariable Long id){
        Collection<Friends> friends=friendsService.findListFriendsByIdFriend(id);
        friends.forEach((friend)->{
            friend.setStatus_friend(Boolean.TRUE);
            friendsService.updateFriend(friend.getId(),friend);
        });
        return new ResponseEntity<>(null,HttpStatus.valueOf(200));
    }
    @PutMapping("/update/statusfalse/{id}")
    public ResponseEntity<?> updateStatusFalse(@PathVariable Long id){
        Collection<Friends> friends=friendsService.findListFriendsByIdFriend(id);
        friends.forEach((friend)->{
            friend.setStatus_friend(Boolean.FALSE);
            friendsService.updateFriend(friend.getId(),friend);
        });
        return new ResponseEntity<>(null,HttpStatus.valueOf(200));
    }
    @GetMapping("/findfriend/{id_me}/{id_group}")
    public ArrayList<Friends> findFriendNotInGroup(@PathVariable Long id_me,@PathVariable String id_group){
        ArrayList<Friends> friends=friendsService.getListFriendsById(id_me);
        ArrayList<GroupChat> gr =grService.getListUserGroupChat(id_group);
        for(int i=0;i<gr.size();i++){
            for(int j=0;j<friends.size();j++){
               if(friends.get(j).getId_friend()==gr.get(i).getId_user()){
                    friends.remove(j);
                }
            }
        }
        
        
        return friends;
    }
    
}
