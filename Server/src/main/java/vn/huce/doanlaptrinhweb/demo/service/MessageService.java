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
import vn.huce.doanlaptrinhweb.demo.model.Message;
import vn.huce.doanlaptrinhweb.demo.repository.MessageRepository;

/**
 *
 * @author tuank
 */
@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepo;
    public Collection<Message> getListMessage(String id){
        return messageRepo.getListMessage(id);
    }
    public void addMessage(Message message){
        messageRepo.save(message);
    }
    public Collection<Message> getListMessageGroup(String receiver ){
        return messageRepo.getListMessageGroup(receiver);
    }
    public ArrayList<Message> getMessageGroup(String receiver ){
        return messageRepo.getMessageGroup(receiver);
    }
}
