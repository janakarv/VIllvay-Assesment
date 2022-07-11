/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.service;

import com.assignment.blogger.model.Author;
import com.assignment.blogger.model.Posts;
import com.assignment.blogger.repository.AuthorRepository;
import com.assignment.blogger.repository.PostsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author admin
 */
@Component
public class PostServiceImpl implements PostsService{
    
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Posts> getAllPost() {
        List<Posts> posts = new ArrayList<Posts>();

        postsRepository.findAll().forEach(posts::add);

        return posts;
    }

    public Posts getPostById(long postid) {

        Optional<Posts> posts = postsRepository.findById(postid);

        return posts.get();
    }

    public Posts savePosts(Long authorId, Posts post) {

        Optional<Author> author = authorRepository.findById(authorId);

        post.setAuthor(author.get());

        Posts _post = postsRepository.save(post);

        return _post;

    }

    public Posts updatePost(long id, Posts posts) {

        Optional<Posts> authData = postsRepository.findById(id);

        if (authData.isPresent()) {
            Posts _post = authData.get();
            _post.setTitle(posts.getTitle());
            _post.setBody(posts.getBody());

            postsRepository.save(_post);
            return _post;
        } else {
            return null;
        }
    }

    public void deletPostById(long id) {

        postsRepository.deleteById(id);

    }

}
