package com.example.spring6webapp.bootstrap;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// When Spring looks at the packages, it will see this and will create a SpringBean for this
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    // Spring Data JPA will provide the implementation for authorRepository and bookRepository
    public BootstrapData(
        AuthorRepository authorRepository,
        BookRepository bookRepository,
        PublisherRepository publisherRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    //CommandLineRunner requires a run method
    @Override
    public void run(String... args) throws Exception {
        Author jrrTolkien = new Author();
        jrrTolkien.setFirstName("JRR");
        jrrTolkien.setLastName("Tolkien");

        Book fellowshipOfTheRing = new Book();
        fellowshipOfTheRing.setTitle("Fellowship of the Ring");
        fellowshipOfTheRing.setIsbn("123456");

        Publisher harperCollins = new Publisher();
        harperCollins.setPublisherName("HarperCollins");
        harperCollins.setAddress("195 Broadway");
        harperCollins.setCity("New York");
        harperCollins.setState("New York");
        harperCollins.setZip("10007");

        Author jrrTolkienSaved = authorRepository.save(jrrTolkien);
        Book fellowshipSaved = bookRepository.save(fellowshipOfTheRing);

        Author frankHerbert = new Author();
        frankHerbert.setFirstName("Frank");
        frankHerbert.setLastName("Herbert");

        Book dune = new Book();
        dune.setTitle("Dune");
        dune.setIsbn("234567");

        Publisher chiltonBooks = new Publisher();
        chiltonBooks.setAddress("1025 Andrew Dr Ste 100");
        chiltonBooks.setCity("West Chester");
        chiltonBooks.setState("PA");
        chiltonBooks.setZip("19380");

        Author frankHerbertSaved = authorRepository.save(frankHerbert);
        Book duneSaved = bookRepository.save(dune);
        Publisher harperCollinsSaved = publisherRepository.save(harperCollins);
        Publisher chiltonBooksSaved = publisherRepository.save(chiltonBooks);

        jrrTolkienSaved.getBooks().add(fellowshipSaved);
        frankHerbertSaved.getBooks().add(duneSaved);



        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
