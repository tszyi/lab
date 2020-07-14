package com.tszyi.main;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.tszyi.entity.Book;

/**
 * 
 * @author tszyi
 * 
 */
public class MainClass {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("crudUnit");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();
      Book book = new Book("9487 JPA教學", "leo", UUID.randomUUID().toString());
      entityManager.persist(book);
      
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      StackTraceElement[] eles = ex.getStackTrace();
      for(StackTraceElement ele: eles){
        System.out.println(ele);
      }
    } finally {
      entityManager.close();
      entityManagerFactory.close();
      System.out.println(entityManagerFactory.isOpen());
    }
  }
}
