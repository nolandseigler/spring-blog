package com.europa.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String addNums(@PathVariable int num1, @PathVariable int num2) {
        return String.format("This is the result of adding %d and %d:  %d", num1, num2, num1 + num2);
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtractNums(@PathVariable int num1, @PathVariable int num2) {
        return String.format("This is the result of subtracting %d from %d:  %d", num1, num2, num1 - num2);
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiplyNums(@PathVariable int num1, @PathVariable int num2) {
        return String.format("This is the result of multiplying %d and %d:  %d", num1, num2, num1 * num2);
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divideNums(@PathVariable int num1, @PathVariable int num2) {
        return String.format("This is the result of dividing %d by %d:  %d", num1, num2, num1 / num2);
    }
}

