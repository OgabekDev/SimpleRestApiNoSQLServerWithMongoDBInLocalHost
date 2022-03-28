package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public HttpEntity<?> getPosts() {
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/create")
    public HttpEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PutMapping("/update/{id}")
    public HttpEntity<Post> updatePost(@PathVariable(value = "id") String id, @RequestBody Post post) {
        Post editedPost = postService.editPost(id, post);
        return ResponseEntity.status(editedPost != null ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(editedPost);
    }

    @GetMapping("/post/{id}")
    public HttpEntity<Post> getPost(@PathVariable(value = "id") String id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.status(post != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(post);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<Boolean> deletePost(@PathVariable(value = "id") String id) {
        boolean deleted = postService.deletePost(id);
        return ResponseEntity.status(deleted ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(deleted);
    }

}
