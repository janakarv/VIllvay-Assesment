/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.controller;

import com.assignment.blogger.model.Comments;
import com.assignment.blogger.model.Posts;
import com.assignment.blogger.response.CommentsBean;
import com.assignment.blogger.response.PostCommentsResponse;
import com.assignment.blogger.response.PostCommetsBean;
import com.assignment.blogger.response.PostResponse;
import com.assignment.blogger.response.PostsBean;
import com.assignment.blogger.response.Response;
import com.assignment.blogger.service.PostServiceImpl;
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
public class PostsController {
    
    @Autowired
    PostServiceImpl postServiceImpl;
    
        /*
    Get All Posts
     */
    @GetMapping("/getAllPost")
    public ResponseEntity<PostResponse> getAllPost() {

        PostResponse response = new PostResponse();
        try {

            List<Posts> posts = postServiceImpl.getAllPost();

            List<PostsBean> plist = new ArrayList<PostsBean>();

            if (posts.isEmpty()) {
                response.setRescode("96");
                response.setResdes("NOT_FOUND");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for (Posts p : posts) {

                PostsBean pb = new PostsBean();

                pb.setId(p.getId());
                pb.setAuthorid(p.getAuthor().getId());
                pb.setTitle(p.getTitle());
                pb.setBody(p.getBody());
                pb.setCreatedOn(p.getCreatedOn());
                pb.setModifiedOn(p.getModifiedOn());

                plist.add(pb);

            }

            response.setRescode("00");
            response.setResdes("Success");
            response.setPosts(plist);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        /*
        Get Posts by Post Id
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("id") long id) {

        PostResponse response = new PostResponse();
        try {
            Posts p = postServiceImpl.getPostById(id);
            List<PostsBean> plist = new ArrayList<PostsBean>();

            if (p != null) {

                PostsBean pb = new PostsBean();

                pb.setId(p.getId());
                pb.setAuthorid(p.getAuthor().getId());
                pb.setTitle(p.getTitle());
                pb.setBody(p.getBody());
                pb.setCreatedOn(p.getCreatedOn());
                pb.setModifiedOn(p.getModifiedOn());

                plist.add(pb);

                response.setRescode("00");
                response.setResdes("Success");
                response.setPosts(plist);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setRescode("96");
                response.setResdes("NOT_FOUND");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
       /*
        Get Posts by Post Id
     */
    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<PostCommentsResponse> getPostCommentsById(@PathVariable("id") long id) {

        PostCommentsResponse response = new PostCommentsResponse();
        try {
            Posts p = postServiceImpl.getPostById(id);

            List<PostCommetsBean> plist = new ArrayList<PostCommetsBean>();

            if (p != null) {

                PostCommetsBean pb = new PostCommetsBean();

                pb.setId(p.getId());
                pb.setAuthorid(p.getAuthor().getId());
                pb.setTitle(p.getTitle());
                pb.setBody(p.getBody());
                pb.setCreatedOn(p.getCreatedOn());
                pb.setModifiedOn(p.getModifiedOn());

                List<CommentsBean> clist = new ArrayList<CommentsBean>();

                for (Comments c : p.getCommentList()) {

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
                pb.setComments(clist);
                plist.add(pb);

                response.setRescode("00");
                response.setResdes("Success");
                response.setPosts(plist);

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
        Create Post
     */
    @PostMapping("/{authorid}/createPosts")
    public ResponseEntity<Response> savePosts(@PathVariable("authorid") Long authorId, @RequestBody Posts post) {

        Response response = new Response();
        try {
            Posts _post = postServiceImpl.savePosts(authorId, post);

            response.setRescode("00");
            response.setResdes("Success");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        /*
   Update post By post Id
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Response> updatePost(@PathVariable("id") long id, @RequestBody Posts posts) {

        Response response = new Response();
        try {
            Posts updatePost = postServiceImpl.updatePost(id, posts);
            if (updatePost != null) {

                response.setRescode("00");
                response.setResdes("Success");
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
        Delete Post By post ID
     */
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Response> deletPostById(@PathVariable("id") long id) {
        Response response = new Response();
        try {
            postServiceImpl.deletPostById(id);
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
