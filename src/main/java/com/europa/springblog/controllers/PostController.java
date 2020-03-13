package com.europa.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String viewAllPosts() {
        return "Posts index page.";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPostById(@PathVariable long postId) {
        return String.format("View post number: %d", postId);
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostCreationForm(@PathVariable int num1, @PathVariable int num2) {
        return "View the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createNewPost() {
        return "Create a new post";
    }
}

