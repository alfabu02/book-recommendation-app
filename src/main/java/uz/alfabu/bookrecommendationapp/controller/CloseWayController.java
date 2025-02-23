package uz.alfabu.bookrecommendationapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/close-way")
public class CloseWayController {

    @GetMapping
    public String closeWay() {
        return "Congratulations! You close the book recommendation";
    }
}
