package com.example.unidad6_ej3.repository;

import com.example.unidad6_ej3.model.Book;
import com.example.unidad6_ej3.model.QBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public interface BookRepository extends
        MongoRepository<Book, String>,
        QuerydslPredicateExecutor<Book> {
    List<Book> findByTitleContains(String title);

    // querydsl methods
    default List<Book> findByTitleContainsDsl(String title) {
        var pred = QBook.book.title.containsIgnoreCase(title);

        return StreamSupport.stream(
                        findAll(pred).spliterator(), false)
                .toList();
    }
}
