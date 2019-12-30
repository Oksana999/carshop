package com.eoksana.onlinecarsshop1.controllers;

import com.eoksana.onlinecarsshop1.entity.Role;
import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.repositories.UserRepository;
import com.eoksana.onlinecarsshop1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(User user, Model model){
//        User userFormDB = userRepository.findUserByUsername(user.getUsername());
//        if(userFormDB !=null){
//            model.addAttribute("message", "User exist");
//        }
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepository.save(user);
//        return "redirect:/login";
//    }
}
