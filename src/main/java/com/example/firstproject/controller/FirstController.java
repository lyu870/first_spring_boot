package com.example.firstproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    // git 테스트
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "dieb");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "dieb");
        return "goodbye";
    }

}