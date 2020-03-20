package com.europa.springblog.repositories;

import com.europa.springblog.models.Post;
import com.europa.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
    public Post findPostById(long id);
    public Post findPostByTitle(String title);
}
