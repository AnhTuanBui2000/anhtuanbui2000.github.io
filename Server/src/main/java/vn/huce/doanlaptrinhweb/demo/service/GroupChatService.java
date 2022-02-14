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
import vn.huce.doanlaptrinhweb.demo.model.GroupChat;
import vn.huce.doanlaptrinhweb.demo.repository.GroupChatRepository;

/**
 *
 * @author tuank
 */
@Service
public class GroupChatService {
    @Autowired
    GroupChatRepository grRepo;
    public void addGroupChat(GroupChat grchat){
        grRepo.save(grchat);
    }
    public Collection<GroupChat> getUserGroupChat(String id_groupchat){
        return grRepo.getUserGroupChat(id_groupchat);
    }
    public ArrayList<GroupChat> getListUserGroupChat(String id_groupchat){
        return grRepo.getListUserGroupChat(id_groupchat);
    }
    public Collection<GroupChat> getGroupChat(Long id_user){
        return grRepo.getGroupChat(id_user);
    }
    public ArrayList<GroupChat>getGroupChatSize(){
        return grRepo.getGroupChatSize();
    }
    public ArrayList<GroupChat> findUser(Long id_user){
        return grRepo.findUser(id_user); 
    }
    public void updateGroup(Long id,GroupChat gr){
        gr.setId(id); 
        grRepo.save(gr);
    }
}
