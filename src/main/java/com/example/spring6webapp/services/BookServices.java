package com.example.spring6webapp.services;

import com.example.spring6webapp.domain.Book;

public interface BookServices {
    Iterable<Book> findAll();
}
