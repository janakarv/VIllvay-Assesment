/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.service;

import com.assignment.blogger.model.Comments;
import com.assignment.blogger.model.Posts;
import com.assignment.blogger.repository.CommentsRepository;
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
public class CommentsServiceImpl implements CommentsService{
    
    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PostsRepository postsRepository;

    public List<Comments> getAllComments() {
        List<Comments> comments = new ArrayList<Comments>();

        commentsRepository.findAll().forEach(comments::add);

        return comments;
    }

    public Comments getCommentsById(long id) {

        Optional<Comments> comments = commentsRepository.findById(id);

        return comments.get();
    }

    public Comments saveCommets(Long postid, Comments comment) {

        Optional<Posts> posts = postsRepository.findById(postid);

        comment.setPost(posts.get());

        Comments _comment = commentsRepository.save(comment);

        return _comment;

    }

    public Comments updateComments(long id, Comments comments) {

        Optional<Comments> commentsData = commentsRepository.findById(id);

        if (commentsData.isPresent()) {
            Comments _comments = commentsData.get();
            _comments.setName(comments.getName());
            _comments.setEmail(comments.getEmail());
            _comments.setBody(comments.getBody());

            commentsRepository.save(_comments);
            return _comments;
        } else {
            return null;
        }
    }

    public void deletCommentsById(long id) {

        commentsRepository.deleteById(id);

    }

}
