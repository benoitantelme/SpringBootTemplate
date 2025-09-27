package com.template.books.repository;

import com.template.books.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findByName(String name);

  @Override
  List<Book> findAll();
}
