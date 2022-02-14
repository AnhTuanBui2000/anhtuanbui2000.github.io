/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.huce.doanlaptrinhweb.demo.model.Friends;
import vn.huce.doanlaptrinhweb.demo.repository.FriendsRepository;

/**
 *
 * @author tuank
 */
@Service
public class FriendsService {
    @Autowired
    FriendsRepository friendsRepo;
    public Collection<Friends> findListFriendsById(Long id_me){
        return friendsRepo.findListFriendsById(id_me);
    }
    public ArrayList<Friends> getListFriendsById(Long id_me){
        return friendsRepo.getListFriendsById(id_me);
    }
    public void deleteFriend(Long id_friend,Long id_me){
      Friends friend=friendsRepo.findFriend(id_friend,id_me);
      friendsRepo.deleteById(friend.getId());
    }
    public void addFriend(Friends friend){
        friendsRepo.save(friend);
    }
    public ArrayList<Friends> findListFriendsByIdFriend(Long id_friend){
        return friendsRepo.findListFriendsByIdFriend(id_friend);
    }
   public void updateFriend(Long id,Friends friend){
       friend.setId(id);
       friendsRepo.save(friend);
   }
}
