package com.template.books.service;

import com.template.books.model.Book;
import com.template.books.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired private BookRepository bookRepository;

  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  public List<Book> findBook() {
    return bookRepository.findAll();
  }
}
