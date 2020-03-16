package com.europa.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
class RollDiceController {

    @GetMapping("/roll-dice")
    public String welcome() {
       return "roll-dice";
    }

    @GetMapping("/roll-dice/{userNumber}")
    public String rollDie(@PathVariable int userNumber, Model model) {
        String outputString = "";
        int dieNumber = getRandomNumberInRange(1, 6);
        if(userNumber == dieNumber) {
            outputString = String.format("You guessed the correct number! The die landed on %d.", dieNumber);
        } else {
            outputString = String.format("You guessed the incorrect number! The die landed on %d and your number was %d.", dieNumber, userNumber);
        }
        model.addAttribute("result", outputString);
        return "die-rolled";
    }

    @GetMapping("/roll-dice/multi/{userNumber}")
    public String rollDice(@PathVariable int userNumber, Model model) {
        String outputString = "";
        int rollsNumber = getRandomNumberInRange(2, 5);
        List<String> resultList = new ArrayList<>();
        resultList.add(String.format("You rolled %d dice.", rollsNumber));
        int numCorrect = 0;
        for(int i = 1; i <= rollsNumber; i++) {
            int dieNumber = getRandomNumberInRange(1, 6);
            if (userNumber == dieNumber) {
                numCorrect++;
                outputString = String.format("You guessed the correct number! The die landed on %d.", dieNumber);
            } else {
                outputString = String.format("You guessed the incorrect number! The die landed on %d and your number was %d.", dieNumber, userNumber);
            }
            resultList.add(outputString);
        }
        resultList.add(String.format("You guessed correctly %d times.", numCorrect));
        model.addAttribute("results", resultList);
        return "dice-rolled";
    }

private static int getRandomNumberInRange(int min, int max) {

    if (min >= max) {
        throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
}

}