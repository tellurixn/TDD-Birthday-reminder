package com.example.tdd.controllers;


import com.example.tdd.models.Friend;
import com.example.tdd.repos.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    FriendRepository friendRepository;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Friend> friends = friendRepository.findAll();
        List<Friend> friendsWithBirthdayThisMonth = friendRepository.findAllFriendsWithBirthdayThisMonth();

        model.addAttribute("friendsWithBirthdayThisMonth", friendsWithBirthdayThisMonth);
        model.addAttribute("friends", friends);

        return "home";
    }
//todo странице нормальный вид придать
//todo сделать список тех у кого скоро др

    @PostMapping("/submitForm")
    public String addNewFriend(@RequestParam String friendName,
                               @RequestParam String friendLastName,
                               @RequestParam LocalDate friendBirthday){

        System.out.println(friendLastName + friendName + friendBirthday);
        friendRepository.save(Friend.builder().
                lastName(friendLastName).
                firstName(friendName).
                birthday(friendBirthday).
                build());

        return "home";
    }
}
