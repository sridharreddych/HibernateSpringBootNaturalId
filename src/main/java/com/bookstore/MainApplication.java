package com.bookstore;

import com.bookstore.entity.Book;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.service.BookstoreService;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            bookstoreService.persistTwoBooks();

            Book book = bookstoreService.fetchFirstBookByNaturalId();
            System.out.println(book);
        };
    }
}
/*
 * 
 * How To Use Hibernate @NaturalId in SpringBoot

Description: This is a SpringBoot application that maps a natural business key using Hibernate @NaturalId.

Key points:

in the entity (e.g., Book), mark the fields (business keys) that should act as natural IDs with @NaturalId; commonly, there is a single such property, but multiple are suppored as well as here.
for non-mutable ids, mark the columns as @NaturalId(mutable = false) and @Column(nullable = false, updatable = false, unique = true, ...)
for mutable ids, mark the columns as @NaturalId(mutable = true) and @Column(nullable = false, updatable = true, unique = true, ...)
override the equals() and hashCode() using the natural id(s)
define a @NoRepositoryBean interface (NaturalRepository) to define two methods, named findBySimpleNaturalId() and findByNaturalId()
provide an implementation for this interface (NaturalRepositoryImpl) relying on Hibernate, Session, bySimpleNaturalId() and byNaturalId() methods
for the entity, write a repository class (e.g., for the Book entity write BookNaturalRepository) that extends the NaturalRepositoryImpl and use it for setting the entity class type and the natural id type (when an entity uses more than one natural ID, the type is not relevant anymore, simply set it to Serializable)
inject this class in your services and call findBySimpleNaturalId() or findByNaturalId()
 * 
 * 
 * 
 */
