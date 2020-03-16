package com.europa.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Random;

@Controller
class RollDiceController {

    @GetMapping("/roll-die/{userNumber}")
    @ResponseBody
    public String viewAllPosts(@PathVariable int userNumber) {
        String outputString = "";
        int dieNumber = getRandomNumberInRange(1, 6);
        if(userNumber == dieNumber) {
            outputString = String.format("You guessed the correct number! The die landed on %d.", dieNumber);
        } else {
            outputString = String.format("You guessed the incorrect number! The die landed on %d and your number was %d.", dieNumber, userNumber);
        }
        return outputString;
    }

//    @GetMapping("/posts/{")
//    @ResponseBody
//    public String viewPostById(@PathVariable long postId) {
//        return String.format("View post number: %d", postId);
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String viewPostCreationForm(@PathVariable int num1, @PathVariable int num2) {
//        return "View the form for creating a post";
//    }
private static int getRandomNumberInRange(int min, int max) {

    if (min >= max) {
        throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
}

}