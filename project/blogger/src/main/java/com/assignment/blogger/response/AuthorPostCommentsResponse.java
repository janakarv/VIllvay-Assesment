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
public class AuthorPostCommentsResponse {
    
    private String rescode;
    private String resdes;
    
    private List<AuthorPostCommentsBean> author;

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

    public List<AuthorPostCommentsBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorPostCommentsBean> author) {
        this.author = author;
    }
    
    
}
