/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.blogger.repository;

import com.assignment.blogger.model.Posts;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author admin
 */
public interface PostsRepository extends CrudRepository<Posts, Long>{
    
}
