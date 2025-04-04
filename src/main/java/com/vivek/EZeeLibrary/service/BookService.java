package com.vivek.EZeeLibrary.service;

import com.vivek.EZeeLibrary.model.Book;
import com.vivek.EZeeLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        if(bookRepository.findById(book.getId()) != null){
            throw new IllegalArgumentException("Book with ID " + book.getId() + " already exists.");
        }
        if(book.getTitle() == null || book.getTitle().isEmpty()){
            throw new IllegalArgumentException("Book title cannot be empty.");
        }

        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id);
    }

    public List<Book> findbyAuthor(String author) {
        return bookRepository.findbyAuthor(author);
    }

    public Book update(Book book) {
        return bookRepository.update(book);
    }

    public void delete(Integer id) {
        bookRepository.delete(id);
    }
}
