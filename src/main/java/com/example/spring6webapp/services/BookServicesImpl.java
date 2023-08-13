package com.example.spring6webapp.services;

import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServicesImpl implements BookServices {
    private final BookRepository bookRepository;

    public BookServicesImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
