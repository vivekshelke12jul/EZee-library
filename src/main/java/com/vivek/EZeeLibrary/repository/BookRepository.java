package com.vivek.EZeeLibrary.repository;

import com.vivek.EZeeLibrary.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    private Map<Integer, Book> store = new HashMap<>();

    public Book save(Book book) {
        store.put(book.getId(), book);
        return book;
    }

    public List<Book> findAll() {
        return store.values().stream().toList();
    }

    public Book findById(Integer id) {
        return store.get(id);
    }

    public List<Book> findbyAuthor(String author) {
        return store.values()
                .stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public Book update(Book book) {
        store.put(book.getId(), book);
        return book;
    }

    public void delete(Integer id) {
        store.remove(id);
    }
}
