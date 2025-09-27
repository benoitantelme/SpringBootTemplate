package com.template.books;

import static org.junit.jupiter.api.Assertions.*;

import com.template.books.model.Book;
import com.template.books.service.BookService;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

  @Autowired BookService service;

  @Test
  @Order(1)
  public void testFindAll() {
    List<Book> bookList = service.findBook();
    assertEquals(5000, bookList.size());
  }
}
