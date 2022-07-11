/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author admin
 */
@Entity
@Table(name="comments")
public class Comments {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postid", nullable = false)
    @JsonIgnore 
    private Posts posts;
    
    private String name;    
    private String email;    
    private String body;
    
    @CreationTimestamp
    @Column(name="createdOn", nullable = false, updatable = false, insertable = true)
    private Timestamp createdOn;
    
    @CreationTimestamp
    @Column(name="modifiedOn", nullable = false, updatable = true, insertable = true)
    private Timestamp modifiedOn=new Timestamp(System.currentTimeMillis());

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Posts getPost() {
        return posts;
    }

    public void setPost(Posts posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    
    
    
}
