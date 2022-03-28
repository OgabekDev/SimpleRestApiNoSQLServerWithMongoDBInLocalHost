package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post editPost(String postId, Post post) {
        Post editedPost = getPostById(postId);
        if (editedPost != null) {
            editedPost.setTitle(post.getTitle());
            editedPost.setBody(post.getBody());
            postRepository.save(editedPost);
            return editedPost;
        }
        return null;
    }

    public boolean deletePost(String postId) {
        Post deletePost = getPostById(postId);

        if (deletePost == null) {
            return false;
        }

        try {
            postRepository.delete(deletePost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
