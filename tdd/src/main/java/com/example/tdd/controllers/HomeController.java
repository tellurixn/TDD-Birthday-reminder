package com.example.tdd.controllers;

import com.example.tdd.forms.AddFriendForm;
import com.example.tdd.models.Friend;
import com.example.tdd.repos.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    FriendRepository friendRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/submitForm")
    public String addNewFriend(@RequestParam String friendName,
                               @RequestParam String friendLastName,
                               @RequestParam LocalDate friendBirthday){

        friendRepository.save(Friend.builder().
                lastName(friendLastName).
                firstName(friendName).
                birthday(friendBirthday).
                build());

        return "home";
    }
}
