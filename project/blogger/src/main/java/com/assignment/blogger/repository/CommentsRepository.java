/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.blogger.repository;

import com.assignment.blogger.model.Comments;
import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author admin
 */
public interface CommentsRepository extends CrudRepository<Comments, Long>{
//public interface CommentsRepository extends JpaRepository<Comments, Long>{
    
//    List<Comments> findByPostId(long postid);
}
