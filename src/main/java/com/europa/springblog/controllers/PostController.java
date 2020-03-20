package com.europa.springblog.controllers;


import com.europa.springblog.services.EmailService;
import com.europa.springblog.models.Post;
import com.europa.springblog.models.User;
import com.europa.springblog.repositories.PostRepo;
import com.europa.springblog.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {


    private PostRepo postDao;
    private UserRepo userDao;
    private EmailService emailService;

    public PostController(PostRepo postDao, UserRepo userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    //SHOW IT JUST AS JSON.
    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getAllPosts() {
        return postDao.findAll();
    }

    @GetMapping("/posts/show")
    public String showAllPosts(Model model) {
        List<Post> postList = postDao.findAll();
        model.addAttribute("postList", postList);
        return "posts/show";
    }
    @GetMapping("/posts/create")
    public String getPostCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User user = userDao.getOne(1L); // just use the first user in the db
        post.setUser(user);
        postDao.save(post);
        String emailSubject = "A post on the blog was made by " + post.getUser().getUsername() + "." + "It is titled " + post.getTitle() + ".";
        String emailBody = "The post on the blog was " + post.getBody();
        emailService.prepareAndSend(post, emailSubject, emailBody);
        return "redirect:/posts/show";
    }

    @GetMapping("/make/user")
    @ResponseBody
    public String makeUser() {
        User newUser = new User();
        newUser.setEmail("bobbyShmurda@gmail.com");
        newUser.setPassword("totallyHashed");
        newUser.setUsername("bobbyShmurda");
        User user = userDao.save(newUser);
        long newUserId = user.getId();
        return String.format("Saving post with id of %d", newUserId);
    }

    @GetMapping("/posts/save")
    @ResponseBody
    public String saveAd() {
        User user = userDao.getOne(1L); // just use the first user in the db
        Post newPost = new Post();
        newPost.setTitle("CORONA.");
        newPost.setBody("Cats are cool");
        newPost.setUser(user);
        Post post = postDao.save(newPost);
        long newPostId = post.getId();
        return String.format("Saving post with id of %d", newPostId);
    }

    @GetMapping("/posts/update")
    public String getUpdatePostForm() {
        return "posts/update";
    }
    @PostMapping("/posts/update")
    public void updatePost(@RequestParam long id, @RequestParam String title, @RequestParam String body) {
        Post post = postDao.findPostById(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
    }


    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model){
        Post post = postDao.findPostById(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String getEditPostForm(@PathVariable long id, Model model){
        Post post = postDao.findPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        Post dbPost = postDao.findPostById(id);
        dbPost.setTitle(post.getTitle());
        dbPost.setBody(post.getBody());
        postDao.save(dbPost);
        return "redirect:/posts/show";
    }

    @DeleteMapping("/posts/delete")
    public String deletePost(@RequestParam long id, Model model){
        Post post = postDao.findPostById(id);
        String deletedTitle = post.getTitle();
        postDao.deleteById(id);
        model.addAttribute("title", "Deleted");
        model.addAttribute("body", deletedTitle);
        return "redirect:/posts/show";
    }
}