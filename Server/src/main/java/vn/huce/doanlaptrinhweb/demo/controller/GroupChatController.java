/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.huce.doanlaptrinhweb.demo.model.GroupChat;
import vn.huce.doanlaptrinhweb.demo.model.Message;
import vn.huce.doanlaptrinhweb.demo.service.GroupChatService;
import vn.huce.doanlaptrinhweb.demo.service.MessageService;

/**
 *
 * @author tuank
 */
@RestController
@CrossOrigin
public class GroupChatController {
    
    @Autowired
    GroupChatService grService;
    @Autowired
    MessageService messageService;
    
    @PostMapping("/add/groupchat")
    public ResponseEntity<?> addGroupChat(@RequestBody GroupChat grchat){
        ArrayList<GroupChat> group = grService.getGroupChatSize();
        int i =group.size();
        int x = Integer.parseInt(group.get(i-1).getId_groupchat().substring(1));
        String y=String.valueOf(x+1);
        grchat.setId_groupchat("a"+y);
        grService.addGroupChat(grchat); 
        return new ResponseEntity<>(null,HttpStatus.valueOf(200));
    }
    @PostMapping("/add/user/groupchat")
    public ResponseEntity<?> addUserGroupChat(@RequestBody GroupChat grchat){
        grService.addGroupChat(grchat); 
        return new ResponseEntity<>(null,HttpStatus.valueOf(200));
    }
    @GetMapping("/getusergroupchat/{id_groupchat}")
    public Collection<GroupChat> getUserGroupChat(@PathVariable String id_groupchat){
        return grService.getUserGroupChat(id_groupchat);
    }
    @GetMapping("/getgroupchat/{id_user}")
    public Collection<GroupChat> getGroupChat(@PathVariable Long id_user){
        return grService.getGroupChat(id_user);
    }
    @GetMapping("/listmessagegroup/{id}")
    public ArrayList<Message> getListMessageGroup(@PathVariable String id){
        ArrayList<GroupChat> gr=grService.getGroupChatSize();
        ArrayList<Message> messages=new ArrayList<Message>();
        gr.forEach((group)->{
            ArrayList<Message> message=messageService.getMessageGroup(group.getId_groupchat());
            message.forEach((mes)->{
                messages.add(mes); 
            });
        });
               
        return messages;
    }
    
}
