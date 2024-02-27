package com.example.unidad6_ej3.dao;

import com.example.unidad6_ej3.model.Book;
import com.example.unidad6_ej3.model.QBook;
import com.example.unidad6_ej3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class BookDAO {
    @Autowired
    private BookRepository repo;

    public List<Book> findByTitleContains(String title){
        var pred = QBook.book.title.containsIgnoreCase(title);
        return StreamSupport.stream(
                        repo.findAll(pred).spliterator(), false)
                .toList();
    }
}
