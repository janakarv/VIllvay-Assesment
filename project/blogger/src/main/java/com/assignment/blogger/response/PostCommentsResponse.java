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
public class PostCommentsResponse {
    
    private String rescode;
    private String resdes;
    
    private List<PostCommetsBean> posts;

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

    public List<PostCommetsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostCommetsBean> posts) {
        this.posts = posts;
    }
    
    
}
