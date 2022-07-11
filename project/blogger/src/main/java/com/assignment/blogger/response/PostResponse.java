/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.response;

import java.util.List;

/**
 *
 * @author admin
 */
public class PostResponse {
    
    private String rescode;
    private String resdes;
    
    private List<PostsBean> posts;

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public String getResdes() {
        return resdes;
    }

    public void setResdes(String resdes) {
        this.resdes = resdes;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }
    
    
    
}
