package com.template.books.model;

import jakarta.persistence.*;

@Entity
@Table(name = "counterparty")
public class Book {

  @Id private String name;

  private String author;

  private Integer pages;

  public Book() {}

  public Book(String name) {
    this.name = name;
  }

  public Book(String name, String author, Integer pages) {
    this.name = name;
    this.author = author;
    this.pages = pages;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }
}
