package com.template.books.service;

import com.template.books.model.Book;
import com.template.books.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  public List<Book> findBooks(int pageNumber, int size) {
    Pageable page = PageRequest.of(pageNumber, size);
    Page<Book> books = bookRepository.findAll(page);
    return books.getContent();
  }
}
