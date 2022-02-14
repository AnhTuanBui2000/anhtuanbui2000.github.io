/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.huce.doanlaptrinhweb.demo.model.User;
import vn.huce.doanlaptrinhweb.demo.repository.UserRepository;
import vn.huce.doanlaptrinhweb.demo.service.UserService;

/**
 *
 * @author tuank
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request,
            @RequestParam(value = "username",
                    defaultValue = "") String username,
            @RequestParam(value = "password",
                    defaultValue = "") String password
    ) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
           if(password.equals(user.getPassword())){
            request.getSession().setAttribute("username", username);
            
            return new ResponseEntity<>(user,
                    HttpStatus.valueOf(200));
            }   
            else {
                return new ResponseEntity<>(null,
                    HttpStatus.valueOf(401));
            }
        } 
        else {
            return new ResponseEntity<>(null,
                    HttpStatus.valueOf(404));
        }
    }
    @CrossOrigin
    @GetMapping("/login/{user}")
    public User getUserLogin(@PathVariable String user){
        return userService.findByUsername(user);
    }
    
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> post(@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername())==null){
        userRepository.save(user);
        return new ResponseEntity<>(null,
                HttpStatus.valueOf(201));
        }
        else return new ResponseEntity<>(null,
                HttpStatus.valueOf(202));
    }
}