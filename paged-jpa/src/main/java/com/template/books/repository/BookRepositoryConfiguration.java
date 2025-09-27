package com.template.books.repository;

import com.template.books.model.Book;
import com.template.books.service.BookService;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookRepositoryConfiguration {

  @Bean
  public CommandLineRunner counterpartyRepositorySetup(BookService bookService) {
    return (args) -> {
      // initial books
      Random random = new Random();
      IntStream.range(0, 5000)
          .mapToObj(i -> new Book("Book no " + i, "Author " + i, random.nextInt(5000)))
          .forEach(bookService::saveBook);
    };
  }
}
