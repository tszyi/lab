package com.tszyi.hibernate.generate.table;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tszyi.entity.Author;
import com.tszyi.entity.Book;

public class MainClass {
  public static void main(String[] args) {
    Configuration cfg = new Configuration();
    cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate_test")
      .setProperty("hibernate.connection.username", "tszyi")
      .setProperty("hibernate.connection.password", "tszyi")
      .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect")
      .setProperty("hibernate.driver_class", "com.mysql.jdbc.Driver")
      .setProperty("hibernate.hbm2ddl.auto", "update")
      .setProperty("hibernate.show_sql", "true")
      .setProperty("hibernate.format_sql", "true")
      .addAnnotatedClass(Author.class)
      .addAnnotatedClass(Book.class);
    SessionFactory sf = cfg.buildSessionFactory();
    sf.close();
  }
}
