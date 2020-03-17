package com.europa.springblog.controllers;


import com.europa.springblog.models.Post;
import com.europa.springblog.repositories.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {


    private PostRepo postDao;

    public PostController(PostRepo postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    @GetMapping("/posts/save")
    @ResponseBody
    public String saveAd() {
        Post newPost = new Post();
        newPost.setTitle("Cats with lemons.");
        newPost.setBody("Cats go on an adventure to the supermarket in order to find lemons for their coronavirus infected owners.");
        Post post = postDao.save(newPost);
        long newPostId = post.getId();
        return String.format("Saving post with id of %d", newPostId);
    }

    @GetMapping("/posts/update")
    @ResponseBody
    public String updatePost() {
        Post post = postDao.getOne(1L);
        post.setTitle("Cats with lemons(Updated)");
        postDao.save(post);
        return "Updating post";
    }


    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model){
        Post post = postDao.findPostById(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model){
        Post post = postDao.findPostById(id);
        String deletedTitle = post.getTitle();
        postDao.deleteById(id);
        model.addAttribute("title", "Deleted");
        model.addAttribute("body", deletedTitle);
        return "posts/show";
    }
}