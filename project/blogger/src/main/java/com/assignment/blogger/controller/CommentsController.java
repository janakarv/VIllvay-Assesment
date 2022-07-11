/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.controller;

import com.assignment.blogger.model.Comments;
import com.assignment.blogger.response.CommentsBean;
import com.assignment.blogger.response.CommentsResponse;
import com.assignment.blogger.response.Response;
import com.assignment.blogger.service.CommentsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/blog")
public class CommentsController {
    
    @Autowired
    CommentsServiceImpl commentsServiceImpl;
    
    /*
    Get All Comments
     */
    @GetMapping("/getAllComments")
    public ResponseEntity<CommentsResponse> getAllComments() {
        CommentsResponse response = new CommentsResponse();
        try {

            List<Comments> comments = commentsServiceImpl.getAllComments();

            List<CommentsBean> clist = new ArrayList<CommentsBean>();

            if (comments.isEmpty()) {
                response.setRescode("96");
                response.setResdes("NO_CONTENT");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }

            for (Comments c : comments) {
                CommentsBean cb = new CommentsBean();

                cb.setId(c.getId());
                cb.setPostid(c.getPost().getId());
                cb.setName(c.getName());
                cb.setEmail(c.getEmail());
                cb.setBody(c.getBody());
                cb.setCreatedOn(c.getCreatedOn());
                cb.setModifiedOn(c.getModifiedOn());

                clist.add(cb);
            }

            response.setRescode("00");
            response.setResdes("Success");
            response.setComments(clist);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Get Comments by Comments Id
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentsResponse> getCommentsById(@PathVariable("id") long id) {

        CommentsResponse response = new CommentsResponse();

        List<CommentsBean> clist = new ArrayList<CommentsBean>();
        try {
            Comments c = commentsServiceImpl.getCommentsById(id);
            if (c != null) {

                CommentsBean cb = new CommentsBean();

                cb.setId(c.getId());
                cb.setPostid(c.getPost().getId());
                cb.setName(c.getName());
                cb.setEmail(c.getEmail());
                cb.setBody(c.getBody());
                cb.setCreatedOn(c.getCreatedOn());
                cb.setModifiedOn(c.getModifiedOn());
                clist.add(cb);

                response.setRescode("00");
                response.setResdes("Success");
                response.setComments(clist);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setRescode("96");
                response.setResdes("NOT_FOUND");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        /*
        Create Comments
     */
    @PostMapping("/{postid}/createComments")
    public ResponseEntity<Response> savePosts(@PathVariable("postid") Long postid, @RequestBody Comments comments) {
        Response response = new Response();
        try {
            Comments _comments = commentsServiceImpl.saveCommets(postid, comments);

            response.setRescode("00");
            response.setResdes("Success");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        /*
   Update Comments By Comments Id
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<Response> updatePost(@PathVariable("id") long id, @RequestBody Comments comments) {

        Response response = new Response();
        try {
            Comments updateComments = commentsServiceImpl.updateComments(id, comments);
            if (updateComments != null) {

                response.setRescode("00");
                response.setResdes("Success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
       
    /*
        Delete Comments By Comments ID
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Response> deletPostById(@PathVariable("id") long id) {
        Response response = new Response();
        try {
            commentsServiceImpl.deletCommentsById(id);
            response.setRescode("00");
            response.setResdes("Success");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}
