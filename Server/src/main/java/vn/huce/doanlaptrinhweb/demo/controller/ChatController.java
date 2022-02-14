/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.controller;


import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.huce.doanlaptrinhweb.demo.model.Message;
import vn.huce.doanlaptrinhweb.demo.service.MessageService;

/**
 *
 * @author tuank
 */
@RestController
public class ChatController {
    //@Autowired
    //private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    MessageService messageService;
/*
    @MessageMapping("/chat")
    public void sendMessage(@RequestBody Message message) {
        System.out.println("handling send message: " + message );
            messageService.addMessage(message); 
        
    }
*/
    @CrossOrigin
    @GetMapping("/listmessage/{id}")
    public Collection<Message> getListChat(@PathVariable String id){
        return messageService.getListMessage(id);
    }
    @CrossOrigin
    @PostMapping("/add/message")
    public ResponseEntity<?> addMessage(@RequestBody Message message){
        messageService.addMessage(message);
        return new ResponseEntity<>(null,HttpStatus.valueOf(200));
    }
    
}
