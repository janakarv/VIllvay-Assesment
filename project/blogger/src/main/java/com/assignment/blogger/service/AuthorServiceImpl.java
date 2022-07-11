/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.blogger.service;

import com.assignment.blogger.model.Author;
import com.assignment.blogger.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author admin
 */
/*
@Service annotation is a specialisation of
@Component annotation. It is used to define service-layer classes.
*/
@Component
public class AuthorServiceImpl implements AuthorService{
    
    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {

        return authorRepository.save(author);

    }

    public List<Author> getAllAuthor() {

        List<Author> author = new ArrayList<Author>();
        authorRepository.findAll().forEach(author::add);

        return author;
    }

    public Author getAuthorById(long id) {

        Optional<Author> authData = authorRepository.findById(id);

        return authData.get();

    }

    public Author updateAuthor(long id, Author author) {

        Optional<Author> authData = authorRepository.findById(id);

        if (authData.isPresent()) {
            Author _author = authData.get();
            _author.setName(author.getName());
            _author.setUsername(author.getUsername());
            _author.setEmail(author.getEmail());
            _author.setAddress(author.getAddress());

            authorRepository.save(_author);
            return _author;
        } else {
            return null;
        }
    }

    public void deleteAuthor(long id) {

        authorRepository.deleteById(id);
    }
}
