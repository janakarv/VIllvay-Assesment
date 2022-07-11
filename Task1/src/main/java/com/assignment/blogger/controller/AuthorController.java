/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.controller;

import com.assignment.blogger.model.Author;
import com.assignment.blogger.model.Comments;
import com.assignment.blogger.model.Posts;
import com.assignment.blogger.response.AuthorBean;
import com.assignment.blogger.response.AuthorPostBean;
import com.assignment.blogger.response.AuthorPostCommentsBean;
import com.assignment.blogger.response.AuthorPostCommentsResponse;
import com.assignment.blogger.response.AuthorPostResponse;
import com.assignment.blogger.response.AuthorResponse;
import com.assignment.blogger.response.CommentsBean;
import com.assignment.blogger.response.PostCommetsBean;
import com.assignment.blogger.response.PostsBean;
import com.assignment.blogger.response.Response;
import com.assignment.blogger.service.AuthorServiceImpl;
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
public class AuthorController {
    
//    @Autowired
//    private AuthorRepository authorRepository;
    
    
    @Autowired
    private AuthorServiceImpl serviceImpl;

    /*
    Get All Author Details
     */
    @GetMapping("/getAllAuthor")
    public ResponseEntity<AuthorResponse> getAllAuthor() {

        AuthorResponse response = new AuthorResponse();
        try {

            List<Author> author = serviceImpl.getAllAuthor();

            List<AuthorBean> alist = new ArrayList<AuthorBean>();

            if (author.isEmpty()) {
                response.setRescode("96");
                response.setResdes("NO_CONTENT");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }

            for (Author a : author) {
                AuthorBean ab = new AuthorBean();

                ab.setId(a.getId());
                ab.setName(a.getName());
                ab.setUsername(a.getUsername());
                ab.setEmail(a.getEmail());
                ab.setAddress(a.getAddress());

                alist.add(ab);

            }

            response.setRescode("00");
            response.setResdes("Success");
            response.setAuthor(alist);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    Get Author By ID
    PathVariable -> /author/1
     */
    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable("id") long id) {

        AuthorResponse response = new AuthorResponse();
        try {
            Author a = serviceImpl.getAuthorById(id);

            List<AuthorBean> alist = new ArrayList<AuthorBean>();
            if (a != null) {

                AuthorBean ab = new AuthorBean();

                ab.setId(a.getId());
                ab.setName(a.getName());
                ab.setUsername(a.getUsername());
                ab.setEmail(a.getEmail());
                ab.setAddress(a.getAddress());

                alist.add(ab);

                response.setRescode("00");
                response.setResdes("Success");
                response.setAuthor(alist);

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
    Get Author posts details by author id
     */
    @GetMapping("/author/{id}/posts")
    public ResponseEntity<AuthorPostResponse> getAuthorPostById(@PathVariable("id") long id) {

        AuthorPostResponse response = new AuthorPostResponse();
        try {
            Author a = serviceImpl.getAuthorById(id);

            List<AuthorPostBean> alist = new ArrayList<AuthorPostBean>();
            if (a != null) {

                AuthorPostBean ab = new AuthorPostBean();

                ab.setId(a.getId());
                ab.setName(a.getName());
                ab.setUsername(a.getUsername());
                ab.setEmail(a.getEmail());
                ab.setAddress(a.getAddress());

                List<PostsBean> plist = new ArrayList<PostsBean>();

                for (Posts p : a.getPostList()) {

                    PostsBean pb = new PostsBean();

                    pb.setId(p.getId());
                    pb.setAuthorid(p.getAuthor().getId());
                    pb.setTitle(p.getTitle());
                    pb.setBody(p.getBody());
                    pb.setCreatedOn(p.getCreatedOn());
                    pb.setModifiedOn(p.getModifiedOn());

                    plist.add(pb);
                }

                ab.setPosts(plist);
                alist.add(ab);

                response.setRescode("00");
                response.setResdes("Success");
                response.setAuthor(alist);

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
    Get Author comments and posts details by author id
     */
    @GetMapping("/author/{id}/posts/comments")
    public ResponseEntity<AuthorPostCommentsResponse> getAuthorPostCommentsById(@PathVariable("id") long id) {

        AuthorPostCommentsResponse response = new AuthorPostCommentsResponse();
        try {
            Author a = serviceImpl.getAuthorById(id);

            List<AuthorPostCommentsBean> alist = new ArrayList<AuthorPostCommentsBean>();
            if (a != null) {

                AuthorPostCommentsBean ab = new AuthorPostCommentsBean();

                ab.setId(a.getId());
                ab.setName(a.getName());
                ab.setUsername(a.getUsername());
                ab.setEmail(a.getEmail());
                ab.setAddress(a.getAddress());

                List<PostCommetsBean> plist = new ArrayList<PostCommetsBean>();

                for (Posts p : a.getPostList()) {

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
                }

                ab.setPosts(plist);
                alist.add(ab);

                response.setRescode("00");
                response.setResdes("Success");
                response.setAuthor(alist);

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
        Create Author
        Save Author Basic details
        
     */
    @PostMapping("/createAuthor")
    public ResponseEntity<Response> saveAuthor(@RequestBody Author author) {
        Response response = new Response();
        try {
            Author _author = serviceImpl.saveAuthor(author);

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
   Update Author By Id
     */
    @PutMapping("/author/{id}")
    public ResponseEntity<Response> updateAuthor(@PathVariable("id") long id, @RequestBody Author author) {

        Response response = new Response();
        try {
            Author updateAuthor = serviceImpl.updateAuthor(id, author);
            if (updateAuthor != null) {
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
        Delete Author By ID
     */
    @DeleteMapping("/author/{id}")
    public ResponseEntity<Response> deleteAuthor(@PathVariable("id") long id) {
        Response response = new Response();
        try {
            serviceImpl.deleteAuthor(id);
            response.setRescode("00");
            response.setResdes("Success");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setRescode("99");
            response.setResdes("INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
