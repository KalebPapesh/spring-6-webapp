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

    // CommandLineRunner requires a run method
    @Override
    public void run(String... args) throws Exception {
        Publisher harperCollins = new Publisher();
        harperCollins.setPublisherName("HarperCollins");
        harperCollins.setAddress("195 Broadway");
        harperCollins.setCity("New York");
        harperCollins.setState("New York");
        harperCollins.setZip("10007");

        Publisher chiltonBooks = new Publisher();
        chiltonBooks.setPublisherName("Chilton Books");
        chiltonBooks.setAddress("1025 Andrew Dr Ste 100");
        chiltonBooks.setCity("West Chester");
        chiltonBooks.setState("PA");
        chiltonBooks.setZip("19380");

        Publisher harperCollinsSaved = publisherRepository.save(harperCollins);
        Publisher chiltonBooksSaved = publisherRepository.save(chiltonBooks);

        Author jrrTolkien = new Author();
        jrrTolkien.setFirstName("JRR");
        jrrTolkien.setLastName("Tolkien");

        Author frankHerbert = new Author();
        frankHerbert.setFirstName("Frank");
        frankHerbert.setLastName("Herbert");

        Author hgWells = new Author();
        hgWells.setFirstName("H.G.");
        hgWells.setLastName("Wells");

        Book fellowshipOfTheRing = new Book();
        fellowshipOfTheRing.setTitle("Fellowship of the Ring");
        fellowshipOfTheRing.setPublisher(harperCollinsSaved);
        fellowshipOfTheRing.setIsbn("123456");

        Book warOfTheWorlds = new Book();
        warOfTheWorlds.setTitle("War of the Worlds");
        warOfTheWorlds.setPublisher(harperCollinsSaved);
        warOfTheWorlds.setIsbn("456789");

        Book dune = new Book();
        dune.setTitle("Dune");
        dune.setPublisher(chiltonBooksSaved);
        dune.setIsbn("234567");

        Book duneMessiah = new Book();
        duneMessiah.setTitle("Dune Messiah");
        duneMessiah.setPublisher(chiltonBooksSaved);
        duneMessiah.setIsbn("345678");

        Author jrrTolkienSaved = authorRepository.save(jrrTolkien);
        Author hgWellsSaved = authorRepository.save(hgWells);
        Author frankHerbertSaved = authorRepository.save(frankHerbert);

        Book fellowshipSaved = bookRepository.save(fellowshipOfTheRing);
        Book warOfTheWorldsSaved = bookRepository.save(warOfTheWorlds);
        Book duneSaved = bookRepository.save(dune);
        Book duneMessiahSaved = bookRepository.save(duneMessiah);

        jrrTolkienSaved.getBooks().add(fellowshipSaved);
        hgWellsSaved.getBooks().add(warOfTheWorldsSaved);
        frankHerbertSaved.getBooks().add(duneSaved);
        frankHerbertSaved.getBooks().add(duneMessiahSaved);
        fellowshipSaved.getAuthors().add(jrrTolkienSaved);
        warOfTheWorldsSaved.getAuthors().add(hgWellsSaved);
        duneSaved.getAuthors().add(frankHerbertSaved);
        duneMessiahSaved.getAuthors().add(frankHerbertSaved);

        // The updated items to include the book -> author relationship
        // and author -> book relationship have to be saved again to update the author_book table
        authorRepository.save(jrrTolkienSaved);
        authorRepository.save(hgWellsSaved);
        authorRepository.save(frankHerbertSaved);

        bookRepository.save(fellowshipSaved);
        bookRepository.save(warOfTheWorldsSaved);
        bookRepository.save(duneSaved);
        bookRepository.save(duneMessiahSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
