package com.template.books.controller;

import com.template.books.model.Book;
import com.template.books.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

  @Autowired BookService bookService;

  @GetMapping("/books")
  public List<Book> getBooks() {
    return bookService.findBook();
  }
}
