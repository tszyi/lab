package com.tszyi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;
  @Column(name = "name", length = 20, nullable = false)
  private String name;
  @Column(name = "author", length = 20, nullable = false)
  private String author;
  @Column(name = "isbn", length = 36, nullable = false)
  private String ISBN;

  public Book() {
    super();
  }

  public Book(String name, String author, String iSBN) {
    super();
    this.name = name;
    this.author = author;
    ISBN = iSBN;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

}
